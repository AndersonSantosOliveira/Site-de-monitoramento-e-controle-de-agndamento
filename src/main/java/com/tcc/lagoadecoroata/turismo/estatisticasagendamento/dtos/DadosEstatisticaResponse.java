package com.tcc.lagoadecoroata.turismo.estatisticasagendamento.dtos;

import java.time.LocalDate;

public record DadosEstatisticaResponse(LocalDate data,
                                       Integer quantidadePessoas,
                                       Integer vagasRestantes,
                                       Double percentualOcupacao) {

}
