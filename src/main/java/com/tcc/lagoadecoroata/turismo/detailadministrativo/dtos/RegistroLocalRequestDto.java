package com.tcc.lagoadecoroata.turismo.detailadministrativo.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RegistroLocalRequestDto(
        @NotBlank(message = "O nome e obrigtorio")
        String nomeResponsavel,
        @NotNull(message = "A Quantidade de pessoas e obrigatório")
        @Min(value = 1, message = "É necessário informar pelo menos uma pessoa.")
        Integer quantidadePessoas
) {
}
