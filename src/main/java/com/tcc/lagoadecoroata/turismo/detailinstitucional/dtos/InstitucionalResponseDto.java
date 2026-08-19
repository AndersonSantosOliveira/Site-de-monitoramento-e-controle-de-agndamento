package com.tcc.lagoadecoroata.turismo.detailinstitucional.dtos;

public record InstitucionalResponseDto(
        SobreLagoaResponseDto sobreLagoaResponseDto,
        OrientacoesVisitantesResponseDto orientacoesVisitantes,
        AvisosResponseDto avisosResponseDto,
        InformacoesResponseDto informacoesResponseDto
) {
}
