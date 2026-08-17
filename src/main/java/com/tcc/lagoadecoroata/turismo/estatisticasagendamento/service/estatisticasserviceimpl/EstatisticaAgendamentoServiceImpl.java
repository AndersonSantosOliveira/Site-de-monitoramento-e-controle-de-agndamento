package com.tcc.lagoadecoroata.turismo.estatisticasagendamento.service.estatisticasserviceimpl;

import com.tcc.lagoadecoroata.turismo.estatisticasagendamento.dtos.DadosEstatisticaResponse;

import java.time.LocalDate;

public interface EstatisticaAgendamentoServiceImpl {
    DadosEstatisticaResponse consultarEstatistica(LocalDate data);

    Integer consultarVagas(LocalDate data);

    Double consultarPercentual(LocalDate data);

    Boolean consultarDisponibilidade(LocalDate data);
}
