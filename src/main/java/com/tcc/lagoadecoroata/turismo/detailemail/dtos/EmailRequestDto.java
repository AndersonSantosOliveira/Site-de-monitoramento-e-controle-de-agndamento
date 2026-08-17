package com.tcc.lagoadecoroata.turismo.detailemail.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequestDto(

        @NotBlank(message = "O destinatário é obrigatório")
        @Email(message = "O destinatário deve possuir um e-mail válido")
        String destinatario,

        @NotBlank(message = "O assunto é obrigatório")
        String assunto,

        @NotBlank(message = "O conteúdo é obrigatório")
        String conteudo
) {
}
