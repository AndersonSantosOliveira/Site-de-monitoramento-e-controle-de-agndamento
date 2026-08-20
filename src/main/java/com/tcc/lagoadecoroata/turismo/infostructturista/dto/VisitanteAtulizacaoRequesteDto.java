package com.tcc.lagoadecoroata.turismo.infostructturista.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record VisitanteAtulizacaoRequesteDto(

        String nome,

        @Size(min = 8, max = 20,
                message = "A senha deve conter entre 8 e 20 caracteres.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
                message = "A senha deve conter pelo menos uma letra maiúscula, uma minúscula, um número e um caractere especial."
        )
        String senha
) {}
