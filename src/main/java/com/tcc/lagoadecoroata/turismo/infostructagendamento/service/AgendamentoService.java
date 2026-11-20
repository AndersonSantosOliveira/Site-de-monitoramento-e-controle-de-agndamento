package com.tcc.lagoadecoroata.turismo.infostructagendamento.service;

import com.tcc.lagoadecoroata.turismo.detailemail.dtos.ConfirmacaoAgendamentoEmailDto;
import com.tcc.lagoadecoroata.turismo.detailemail.service.EmailService;
import com.tcc.lagoadecoroata.turismo.detailqrcode.dto.QrCodeResponseDto;
import com.tcc.lagoadecoroata.turismo.detailqrcode.service.QrCodeService;
import com.tcc.lagoadecoroata.turismo.infocontrolelagoa.service.ControleLagoaService;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos.AgendamentoRequestDto;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos.AgendamentoResponseDto;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos.CapacidadeResponseDto;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.entity.Agendamento;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.entity.StatusAgendamento;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.repository.AgendamentoRepository;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.service.impl.AgendamentoServiceImpl;
import com.tcc.lagoadecoroata.turismo.infostructturista.entity.Visitante;
import com.tcc.lagoadecoroata.turismo.infostructturista.reppository.VisitanteRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AgendamentoService implements AgendamentoServiceImpl {
    private final AgendamentoRepository agendamentoRepository;
    private final VisitanteRepository visitanteRepository;
    private final ControleLagoaService controleLagoaService;
    private final QrCodeService qrCodeService;
    private final EmailService emailService;

    public AgendamentoService(AgendamentoRepository agendamento, VisitanteRepository visitante,
                              ControleLagoaService controleLagoaService, QrCodeService qrCodeService, EmailService emailService) {
        this.agendamentoRepository = agendamento;
        this.visitanteRepository = visitante;
        this.controleLagoaService = controleLagoaService;
        this.qrCodeService = qrCodeService;
        this.emailService = emailService;
    }

    private static final int CAPACIDADE_MAXIMA = 50;

    @Override
    @Transactional
    public Agendamento criarAgendamento(AgendamentoRequestDto dto) {

        Visitante visitante = visitanteRepository.findById(dto.visitanteId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Visitante não encontrado."));

        if (agendamentoRepository.existsByVisitanteIdAndData(dto.visitanteId(), dto.data())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "O visitante já possui um agendamento para esta data.");
        }

        CapacidadeResponseDto capacidade =
                verificarCapacidade(dto.data(), dto.quantidadePessoas());

        if (!capacidade.disponivel()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Capacidade máxima atingida."
            );
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setVisitante(visitante);
        agendamento.setData(dto.data());
        agendamento.setQuantidadePessoas(dto.quantidadePessoas());

        agendamento.setStatus(StatusAgendamento.CONFIRMADO);
        agendamento.setCodigoReserva(gerarCodigoReserva());
        QrCodeResponseDto qrCode = qrCodeService.gerarQrCode(agendamento.getCodigoReserva());
        agendamento.setDataCriacao(LocalDateTime.now());
        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);

        ConfirmacaoAgendamentoEmailDto confirmacao = new ConfirmacaoAgendamentoEmailDto(
                visitante.getNome(), agendamentoSalvo.getCodigoReserva(), agendamentoSalvo.getData()
                , agendamentoSalvo.getQuantidadePessoas(), qrCode.getQrCode()
        );
        try {
            emailService.enviarConfirmacaoAgendamento(agendamentoSalvo.getVisitante().getEmail(), confirmacao);
        } catch (MessagingException e) {
            throw new RuntimeException("Não foi possível enviar o e-mail de confirmação do agendamento.", e);
        }
        return agendamentoSalvo;
    }


    @Transactional
    @Override
    public AgendamentoResponseDto cancelarAgendamento(Long id) {

        Agendamento agendamento = agendamentoRepository.findByIdComVisitante(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Agendamento não encontrado"
                ));

        if (agendamento.getStatus() == StatusAgendamento.CANCELADO) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este agendamento já está cancelado."
            );
        }

        if (agendamento.getData().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Não é possível cancelar um agendamento cuja data já passou."
            );
        }

        if (agendamento.getStatus() == StatusAgendamento.FINALIZADO) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Não é possível cancelar um agendamento finalizado."
            );
        }

        agendamento.setStatus(StatusAgendamento.CANCELADO);

        agendamentoRepository.save(agendamento);

        return new AgendamentoResponseDto(agendamento);
    }



    @Override
    public Page<AgendamentoResponseDto> listarAgendamentos(Pageable pageable) {
        return agendamentoRepository.findAllComVisitante
                (pageable).map(AgendamentoResponseDto::new);
    }

    @Override
    public List<AgendamentoResponseDto> listarAgendamentosPorData(LocalDate data) {

        return agendamentoRepository.findByData(data)
                .stream()
                .map(AgendamentoResponseDto::new)
                .toList();
    }


    @Override
    public CapacidadeResponseDto verificarCapacidade(LocalDate data, Integer quantidadePessoas) {

        int pessoasAgendadas = agendamentoRepository.somarPessoasNaData(data);

        int vagasRestantes = CAPACIDADE_MAXIMA - pessoasAgendadas;
        int vagasAposReserva = vagasRestantes - quantidadePessoas;
        boolean disponivel = vagasRestantes >= quantidadePessoas;

        return new CapacidadeResponseDto(disponivel, vagasRestantes, vagasAposReserva);
    }

    @Transactional
    @Override
    public String gerarCodigoReserva() {
        String codigo;
        do {
            codigo = UUID.randomUUID().toString().substring(0, 14).toUpperCase();
        } while (agendamentoRepository.existsByCodigoReserva(codigo));
        return codigo;
    }

}

