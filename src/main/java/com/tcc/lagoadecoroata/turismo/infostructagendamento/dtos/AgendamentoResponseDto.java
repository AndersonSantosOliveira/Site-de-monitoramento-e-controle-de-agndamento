package com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos;

import com.tcc.lagoadecoroata.turismo.infostructagendamento.entity.Agendamento;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.entity.StatusAgendamento;

import java.time.LocalDate;

public record AgendamentoResponseDto(

        Long id,
        String codigoReserva,
        String nomeVisitante,
        LocalDate data,
        Integer quantidadePessoas,
        StatusAgendamento status

) {

    public AgendamentoResponseDto(Agendamento agendamento) {
        this(
                agendamento.getId(),
                agendamento.getCodigoReserva(),
                agendamento.getVisitante().getNome(),
                agendamento.getData(),
                agendamento.getQuantidadePessoas(),
                agendamento.getStatus()
        );
    }}
