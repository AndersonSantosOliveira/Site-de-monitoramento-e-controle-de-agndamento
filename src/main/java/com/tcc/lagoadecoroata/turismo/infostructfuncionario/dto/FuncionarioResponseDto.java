package com.tcc.lagoadecoroata.turismo.infostructfuncionario.dto;

import com.tcc.lagoadecoroata.turismo.infostructfuncionario.entity.Fucionario;

public record FuncionarioResponseDto(
        Long id,
        String nome,
        String email) {
    public FuncionarioResponseDto(Fucionario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail());
    }

}
