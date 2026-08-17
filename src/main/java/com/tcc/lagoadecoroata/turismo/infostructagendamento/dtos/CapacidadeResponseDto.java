package com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos;


public record CapacidadeResponseDto(
        boolean disponivel,
        int vagasRestantes,
        int vagasAposReserva
) {

}
