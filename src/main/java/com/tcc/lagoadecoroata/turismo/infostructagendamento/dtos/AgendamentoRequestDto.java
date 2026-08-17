package com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AgendamentoRequestDto(

        @NotNull(message = "O visitante é obrigatório.")
        Long visitanteId,

        @NotNull(message = "A data do agendamento é obrigatória.")
        @FutureOrPresent(message = "A data não pode ser anterior à data atual.")
        LocalDate data,

        @NotNull(message = "A quantidade de pessoas é obrigatória.")
        @Min(value = 1, message = "É necessário informar pelo menos uma pessoa.")
        Integer quantidadePessoas

) {
}