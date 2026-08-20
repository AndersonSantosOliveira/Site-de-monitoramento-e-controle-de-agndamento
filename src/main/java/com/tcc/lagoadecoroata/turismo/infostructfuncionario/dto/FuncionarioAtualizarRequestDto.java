package com.tcc.lagoadecoroata.turismo.infostructfuncionario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FuncionarioAtualizarRequestDto(

        String nome,
        @Email(message = "Informe um e-mail válido.")
        String email,

        @Size(min = 8, message = "A senha deve possuir no mínimo 8 caracteres.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).*$",
                message = "A senha deve conter letra maiúscula, minúscula, número e caractere especial."
        )
        String senha
) {
}