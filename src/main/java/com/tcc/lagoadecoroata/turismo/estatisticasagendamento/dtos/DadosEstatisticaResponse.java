package com.tcc.lagoadecoroata.turismo.estatisticasagendamento.dtos;

import java.time.LocalDate;

public record DadosEstatisticaResponse(LocalDate data,
                                       Integer agendados,
                                       Integer vagasRestantes,
                                       Double percentualOcupacao) {
}
