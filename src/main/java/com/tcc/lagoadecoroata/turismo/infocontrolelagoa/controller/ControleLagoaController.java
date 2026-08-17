package com.tcc.lagoadecoroata.turismo.infocontrolelagoa.controller;

import com.tcc.lagoadecoroata.turismo.infocontrolelagoa.dtos.DadosControlePercentualResponse;
import com.tcc.lagoadecoroata.turismo.infocontrolelagoa.dtos.DadosControleResponse;
import com.tcc.lagoadecoroata.turismo.infocontrolelagoa.service.ControleLagoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/controle")
public class ControleLagoaController {

    private final ControleLagoaService controleLagoaService;

    public ControleLagoaController(ControleLagoaService controleLagoaService) {
        this.controleLagoaService = controleLagoaService;
    }
    @GetMapping("/ocupacao")
    public ResponseEntity <DadosControleResponse> consultarOcupacaoAtual(){
        var ocupacaoAtual = controleLagoaService.consultarOcupacaoAtual();
        return ResponseEntity.ok(new DadosControleResponse(ocupacaoAtual));
  }
    @GetMapping("/vagas")
    public ResponseEntity <DadosControleResponse> consultarVagasRestantes(){
        var vagasRestantes = controleLagoaService.consultarVagasRestantes();
        return ResponseEntity.ok(new DadosControleResponse(vagasRestantes));
    }

    @GetMapping("/percentual")
    public ResponseEntity <DadosControlePercentualResponse> consultarPercentualOcupacao(){
        return ResponseEntity.ok(new DadosControlePercentualResponse
                (controleLagoaService.consultarPercentualOcupacao()));
    }

}