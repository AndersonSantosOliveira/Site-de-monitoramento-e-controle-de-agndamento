package com.tcc.lagoadecoroata.turismo.infocontrolelagoa.service;

import com.tcc.lagoadecoroata.turismo.infocontrolelagoa.entity.ControleLagoa;
import com.tcc.lagoadecoroata.turismo.infocontrolelagoa.repository.ControleLagoaRepository;
import com.tcc.lagoadecoroata.turismo.infocontrolelagoa.service.controleimpl.ControleLagoaServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;
@Service
public class ControleLagoaService implements ControleLagoaServiceImpl {
    private final ControleLagoaRepository controleLagoaRepository;

    private static final int CAPACIDADE_MAXIMA = 50;

    public ControleLagoaService(ControleLagoaRepository controleLagoaRepository) {
        this.controleLagoaRepository = controleLagoaRepository;
    }

    @Transactional
    @Override
    public void registrarCheckIn(Integer quantidadePessoas) {
        if (quantidadePessoas == null || quantidadePessoas <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Quantidade de pessoas inválida."
            );
        }
        ControleLagoa controleLagoa;

        Optional<ControleLagoa> controle =
                controleLagoaRepository.findByData(LocalDate.now());

        if (controle.isPresent()) {
            controleLagoa = controle.get();
        } else {
            controleLagoa = new ControleLagoa();
            controleLagoa.setData(LocalDate.now());
            controleLagoa.setOcupacaoAtual(0);
            controleLagoa.setTotalCheckins(0);
            controleLagoa.setTotalCheckouts(0);
        }
        if (controleLagoa.getOcupacaoAtual() + quantidadePessoas > CAPACIDADE_MAXIMA) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ocupação está acima do limite");
        }
        controleLagoa.setOcupacaoAtual(controleLagoa.getOcupacaoAtual() + quantidadePessoas);
        controleLagoa.setTotalCheckins(controleLagoa.getTotalCheckins() + quantidadePessoas);

        controleLagoaRepository.save(controleLagoa);
    }

    @Transactional
    @Override
    public void registrarCheckOut(Integer quantidadePessoas) {
        if (quantidadePessoas == null || quantidadePessoas <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Quantidade de pessoas inválida."
            );
        }

        Optional<ControleLagoa> controle =
                controleLagoaRepository.findByData(LocalDate.now());

        if (controle.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Não há registro de ocupação para hoje."
            );
        }

        ControleLagoa controleLagoa = controle.get();

        if (controleLagoa.getOcupacaoAtual() < quantidadePessoas) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A ocupação não pode ficar negativa."
            );
        }

        controleLagoa.setOcupacaoAtual(
                controleLagoa.getOcupacaoAtual() - quantidadePessoas
        );

        controleLagoa.setTotalCheckouts(
                controleLagoa.getTotalCheckouts() + quantidadePessoas
        );

        controleLagoaRepository.save(controleLagoa);
    }

    @Override
    public Integer consultarOcupacaoAtual() {
      return controleLagoaRepository.
              findByData(LocalDate.now()).map(ControleLagoa::getOcupacaoAtual).orElse(0);
    }

    @Override
    public Integer consultarVagasRestantes() {
       Optional<ControleLagoa> controleLagoa = controleLagoaRepository.findByData(LocalDate.now());
       if (controleLagoa.isEmpty()){
           return CAPACIDADE_MAXIMA;
       }
       ControleLagoa controle = controleLagoa.get();
      return CAPACIDADE_MAXIMA - controle.getOcupacaoAtual();
    }

    @Override
    public Double consultarPercentualOcupacao() {
        Optional<ControleLagoa> controleLagoa = controleLagoaRepository.findByData(LocalDate.now());
        if (controleLagoa.isEmpty()){
            return 0.0;
        }
        ControleLagoa controle = controleLagoa.get();
        return controle.getOcupacaoAtual() * 100.0 / CAPACIDADE_MAXIMA;
    }
}
