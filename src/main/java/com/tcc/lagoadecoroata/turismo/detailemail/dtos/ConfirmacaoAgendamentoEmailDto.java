package com.tcc.lagoadecoroata.turismo.detailemail.dtos;

import java.time.LocalDate;

public record ConfirmacaoAgendamentoEmailDto(
        String nomeVisitante,
        String codigoReserva,
        LocalDate data,
        Integer quantidadePessoas,
        byte[] qrCode
) {
}
