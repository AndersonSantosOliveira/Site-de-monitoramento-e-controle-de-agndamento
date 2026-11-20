package com.tcc.lagoadecoroata.turismo.detailadministrativo.service;

import com.tcc.lagoadecoroata.turismo.detailadministrativo.dtos.RegistroLocalRequestDto;
import com.tcc.lagoadecoroata.turismo.detailadministrativo.entity.RegistroLocal;
import com.tcc.lagoadecoroata.turismo.detailadministrativo.entity.StatusAndRegistro;
import com.tcc.lagoadecoroata.turismo.detailadministrativo.repository.RegistroLocalRepository;
import com.tcc.lagoadecoroata.turismo.detailadministrativo.service.adminserviceimpl.AdiministrativoServiceImpl;
import com.tcc.lagoadecoroata.turismo.infocontrolelagoa.service.ControleLagoaService;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos.AgendamentoResponseDto;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.entity.Agendamento;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.entity.StatusAgendamento;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.repository.AgendamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.UUID;
@Service
public class AdministrativoService implements AdiministrativoServiceImpl {

    private final AgendamentoRepository agendamentoRepository;
    private final ControleLagoaService controleLagoaService;
    private final RegistroLocalRepository registroLocal;

    public AdministrativoService(RegistroLocalRepository registroLocal, AgendamentoRepository
            agendamentoRepository, ControleLagoaService controleLagoaService) {
        this.registroLocal = registroLocal;
        this.agendamentoRepository = agendamentoRepository;
        this.controleLagoaService = controleLagoaService;
    }

    @Transactional
    @Override
    public void realizarCheckIn(String codigoReserva) {
        var agendamento = agendamentoRepository.findByCodigoReserva(codigoReserva).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Agendamento não encontrado."
                ));
        if (agendamento.getStatus() != StatusAgendamento.CONFIRMADO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O agendamento não permite realizar check-in.");
        }

        if (!agendamento.getData().equals(LocalDate.now())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A reserva não é válida para hoje."
            );
        }
        agendamento.setStatus(StatusAgendamento.CHECK_IN);
        agendamentoRepository.save(agendamento);
        controleLagoaService.registrarCheckIn(agendamento.getQuantidadePessoas());
    }

    @Transactional
    @Override
    public void realizarCheckOut(String codigoReserva) {
        var agendamento = agendamentoRepository.findByCodigoReserva(codigoReserva)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Agendamento não encontrado."));

        if (agendamento.getStatus() != StatusAgendamento.CHECK_IN) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "O visitante ainda não realizou o check-in."
            );

        }
        if (!agendamento.getData().equals(LocalDate.now())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A reserva não é válida para hoje."
            );
        }
        agendamento.setStatus(StatusAgendamento.FINALIZADO);
        agendamentoRepository.save(agendamento);
        controleLagoaService.registrarCheckOut(agendamento.getQuantidadePessoas());
    }


    @Override
    public AgendamentoResponseDto buscarPorCodigoReserva(String codigoReserva) {
        Agendamento agendamento = agendamentoRepository.findByCodigoReserva(codigoReserva)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Agendamento não encontrado."
                ));

        return new AgendamentoResponseDto(agendamento);
    }

    @Transactional
    @Override
    public RegistroLocal registrarEntradaLocal(RegistroLocalRequestDto registroDto) {
        RegistroLocal registro = new RegistroLocal();
        registro.setNomeResponsavel(registroDto.nomeResponsavel());
        registro.setQuantidadePessoas(registroDto.quantidadePessoas());
        registro.setCodigoRegistro(gerarCodigo());
        registro.setData(LocalDate.now());
        registro.setStatus(StatusAndRegistro.ATIVO);
        controleLagoaService.registrarCheckIn(registro.getQuantidadePessoas());
       return registroLocal.save(registro);
    }

    private String gerarCodigo() {
        String codigo;
        do {
            codigo = UUID.randomUUID().toString().substring(0, 14).toUpperCase();
        } while (registroLocal.existsByCodigoRegistro(codigo));
        return codigo;
    }

    @Transactional
    @Override
    public void realizarSaidaLocal(String codigo) {
        RegistroLocal registro = registroLocal.findByCodigoRegistro(codigo).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Registro local não encontrado."));
        if(registro.getStatus() != StatusAndRegistro.ATIVO){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Esse registro já foi finalizado.");
        }
        controleLagoaService.registrarCheckOut(registro.getQuantidadePessoas());
        registro.setStatus(StatusAndRegistro.FINALIZADO);
        registroLocal.save(registro);
    }
}
