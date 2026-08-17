package com.tcc.lagoadecoroata.turismo.estatisticasagendamento.controller;

import com.tcc.lagoadecoroata.turismo.estatisticasagendamento.dtos.DadosEstatisticaResponse;
import com.tcc.lagoadecoroata.turismo.estatisticasagendamento.service.EstatisticasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaAgendamentoController {
    private final EstatisticasService estatisticasService;

    public EstatisticaAgendamentoController(EstatisticasService estatisticasService){
        this.estatisticasService = estatisticasService;
    }
    @GetMapping("/data/{data}")
    public ResponseEntity<DadosEstatisticaResponse> consultarEstatistica(@PathVariable LocalDate data){
        var receberEstatisticas = estatisticasService.consultarEstatistica(data);
        return ResponseEntity.ok(receberEstatisticas);
    }

    @GetMapping("/vagas/{data}")
    public ResponseEntity<Integer> consultarVagas(@PathVariable LocalDate data){
        var vagas = estatisticasService.consultarVagas(data);
        return ResponseEntity.ok(vagas);
    }
    @GetMapping("/percentual/{data}")
    public ResponseEntity<Double> consultarPercentual(@PathVariable LocalDate data){
        var percentual = estatisticasService.consultarPercentual(data);
        return ResponseEntity.ok(percentual);
    }
    @GetMapping("/disponibilidade/{data}")
    public ResponseEntity<Boolean>consultarDisponibilidade(@PathVariable LocalDate data){
        var disponibilidade = estatisticasService.consultarDisponibilidade(data);
        return ResponseEntity.ok(disponibilidade);
    }

}
