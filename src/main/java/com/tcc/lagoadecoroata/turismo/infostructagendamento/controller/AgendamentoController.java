package com.tcc.lagoadecoroata.turismo.infostructagendamento.controller;

import com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos.AgendamentoRequestDto;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos.AgendamentoResponseDto;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos.CapacidadeResponseDto;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.entity.Agendamento;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.service.AgendamentoService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDto> cadastrarAgendamento(
            @RequestBody @Valid AgendamentoRequestDto dto,
            UriComponentsBuilder uriBuilder) {

        Agendamento agendamento = agendamentoService.criarAgendamento(dto);

        var uri = uriBuilder.path("/agendamento/{id}")
                .buildAndExpand(agendamento.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(new AgendamentoResponseDto(agendamento));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<AgendamentoResponseDto> cancelarAgendamento(
            @PathVariable Long id) {
        var agendamento = agendamentoService.cancelarAgendamento(id);

        return ResponseEntity.ok(agendamento);
    }

    @GetMapping
    public ResponseEntity<Page<AgendamentoResponseDto>> listarAgendamentos(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        var page = agendamentoService.listarAgendamentos(pageable);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<List<AgendamentoResponseDto>> listarPorData(
            @PathVariable LocalDate data) {

        var lista = agendamentoService.listarAgendamentosPorData(data);

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/capacidade")
    public ResponseEntity<CapacidadeResponseDto> verificarCapacidade(
            @RequestParam LocalDate data,
            @RequestParam Integer quantidadePessoas) {

        var capacidade = agendamentoService.verificarCapacidade(data, quantidadePessoas);

        return ResponseEntity.ok(capacidade);
    }

}