package com.tcc.lagoadecoroata.turismo.detailadministrativo.dtos;

import com.tcc.lagoadecoroata.turismo.detailadministrativo.entity.RegistroLocal;
import com.tcc.lagoadecoroata.turismo.detailadministrativo.entity.StatusAndRegistro;

import java.time.LocalDate;

public record RegistroLocalResponseDto(
        Long id,
        String nomeResponsavel,
        Integer quantidadePessoas,
        String codigoRegistro,
        LocalDate data,
        StatusAndRegistro status
) {
    public RegistroLocalResponseDto(RegistroLocal registro) {
        this(
                registro.getId(),
                registro.getNomeResponsavel(),
                registro.getQuantidadePessoas(),
                registro.getCodigoRegistro(),
                registro.getData(),
                registro.getStatus());
    }
}
