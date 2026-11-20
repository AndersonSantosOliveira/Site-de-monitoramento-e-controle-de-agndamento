package com.tcc.lagoadecoroata.turismo.detailadministrativo.controller;

import com.tcc.lagoadecoroata.turismo.detailadministrativo.dtos.RegistroLocalRequestDto;

import com.tcc.lagoadecoroata.turismo.detailadministrativo.dtos.RegistroLocalResponseDto;
import com.tcc.lagoadecoroata.turismo.detailadministrativo.entity.RegistroLocal;
import com.tcc.lagoadecoroata.turismo.detailadministrativo.service.AdministrativoService;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos.AgendamentoResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/admin")
public class AdministrativoController {
    private final AdministrativoService administrativoService;

    public AdministrativoController(AdministrativoService administrativoService) {
        this.administrativoService = administrativoService;
    }

    @PutMapping("/check-in/{codigo}")
    public ResponseEntity<Void> realizarCheckIn(@PathVariable String codigo) {

        administrativoService.realizarCheckIn(codigo);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/check-out/{codigo}")
    public ResponseEntity<Void> realizarCheckOut(@PathVariable String codigo) {
        administrativoService.realizarCheckOut(codigo);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<AgendamentoResponseDto> buscarPorCodigo(@PathVariable String codigo) {
        var agendamento = administrativoService.buscarPorCodigoReserva(codigo);
        return ResponseEntity.ok(agendamento);
    }

    @PostMapping("/registro")
    public ResponseEntity<RegistroLocalResponseDto> realizarRegistro
            (@Valid @RequestBody RegistroLocalRequestDto registroRequest, UriComponentsBuilder uriBuilder) {
        RegistroLocal registro = administrativoService.registrarEntradaLocal(registroRequest);
        var uri = uriBuilder.path("/admin/registro/{id}").buildAndExpand(registro.getId()).toUri();
        return ResponseEntity.created(uri).body(new RegistroLocalResponseDto(registro));
    }

    @PutMapping("/registro/{codigo}/saida")
    public ResponseEntity<Void> registrarSaida(@PathVariable String codigo) {
        administrativoService.realizarSaidaLocal(codigo);
        return ResponseEntity.noContent().build();
    }
}