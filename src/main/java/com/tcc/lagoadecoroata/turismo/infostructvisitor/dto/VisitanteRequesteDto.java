package com.tcc.lagoadecoroata.turismo.infostructvisitor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record VisitanteRequesteDto(
        @NotBlank(message = "O nome é obrigatório.")
        @Pattern(
                regexp = "^[A-Za-zÀ-ÿ ]+$",
                message = "O nome deve conter apenas letras e espaços."
        )
        String nome,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Informe um e-mail válido.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 8, max = 20,
                message = "A senha deve conter entre 8 e 20 caracteres.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
                message = "A senha deve conter pelo menos uma letra maiúscula, uma minúscula, um número e um caractere especial."
        )
        String senha,

        @NotBlank(message = "O telefone é obrigatório.")
        @Pattern(
                regexp = "^\\d{11}$",
                message = "O telefone deve conter exatamente 11 dígitos."
        )
        String telefone
) {
}
