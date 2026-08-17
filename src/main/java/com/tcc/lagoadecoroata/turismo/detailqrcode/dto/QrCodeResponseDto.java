package com.tcc.lagoadecoroata.turismo.detailqrcode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class QrCodeResponseDto {

    private String codigoReserva;
    private byte[] qrCode;
}
