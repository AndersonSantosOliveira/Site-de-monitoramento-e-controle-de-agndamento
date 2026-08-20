package com.tcc.lagoadecoroata.turismo.infostructturista.dto;

import com.tcc.lagoadecoroata.turismo.infostructturista.entity.Visitante;

public record VisitanteResponse(Long id, String nome, String email) {
    public VisitanteResponse(Visitante visitante){
        this(visitante.getId(), visitante.getNome(), visitante.getEmail());
    }
}
