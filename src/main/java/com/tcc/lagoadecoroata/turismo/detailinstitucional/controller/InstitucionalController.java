package com.tcc.lagoadecoroata.turismo.detailinstitucional.controller;

import com.tcc.lagoadecoroata.turismo.detailinstitucional.dtos.InstitucionalResponseDto;
import com.tcc.lagoadecoroata.turismo.detailinstitucional.service.InstitucionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultar")
public class InstitucionalController {
    @Autowired
    private InstitucionalService institucionalService;

    @GetMapping("/institucional")
    public ResponseEntity<InstitucionalResponseDto> consultarInstitucional() {
        return ResponseEntity.ok(institucionalService.consultarInstitucional());

    }
}


