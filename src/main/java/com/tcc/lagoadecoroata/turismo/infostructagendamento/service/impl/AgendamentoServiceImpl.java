package com.tcc.lagoadecoroata.turismo.infostructagendamento.service.impl;

import com.google.zxing.WriterException;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos.AgendamentoRequestDto;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos.AgendamentoResponseDto;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos.CapacidadeResponseDto;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.entity.Agendamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AgendamentoServiceImpl {
    Agendamento criarAgendamento(AgendamentoRequestDto dto) throws IOException, WriterException;

    AgendamentoResponseDto cancelarAgendamento(Long id);

    AgendamentoResponseDto buscarPorCodigoReserva(String codigoReserva);

    Page<AgendamentoResponseDto> listarAgendamentos(Pageable pageable);

    List<AgendamentoResponseDto> listarAgendamentosPorData(LocalDate data);

    CapacidadeResponseDto verificarCapacidade(LocalDate data, Integer quantidadePessoas);


    String gerarCodigoReserva();

    void realizarCheckIn(String codigoReserva);

    void realizarCheckOut(String codigoReserva);
}
