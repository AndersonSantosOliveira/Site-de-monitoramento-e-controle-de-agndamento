package com.tcc.lagoadecoroata.turismo.infostructvisitor.controller;

import com.tcc.lagoadecoroata.turismo.infostructvisitor.dto.VisitanteAtulizacaoRequesteDto;
import com.tcc.lagoadecoroata.turismo.infostructvisitor.dto.VisitanteRequesteDto;
import com.tcc.lagoadecoroata.turismo.infostructvisitor.dto.VisitanteResponse;
import com.tcc.lagoadecoroata.turismo.infostructvisitor.entity.Visitante;
import com.tcc.lagoadecoroata.turismo.infostructvisitor.service.VisitanteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = "/visitantes")
public class VisitanteController {

    private final VisitanteService visitanteService;

    public VisitanteController(VisitanteService visitanteService) {
        this.visitanteService = visitanteService;
    }

    @PostMapping
    public ResponseEntity<VisitanteResponse> criarVizitante(
            @RequestBody @Valid VisitanteRequesteDto visitanteRequesteDto,
            UriComponentsBuilder uriBuilder) {

        Visitante visitante = visitanteService.salvarCadastroVisitante(visitanteRequesteDto);
        var uri = uriBuilder.path("/visitantes/{id}")
                .buildAndExpand(visitante.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new VisitanteResponse(visitante));
    }

    //Metodo de atualizacao de visitantes
    @PutMapping("/{id}")
    public ResponseEntity<VisitanteResponse> atulizarVizitante(
            @PathVariable Long id,
            @RequestBody @Valid VisitanteAtulizacaoRequesteDto visitanteRequeste) {

        Visitante visitante = visitanteService.dadosAtualizacaoVIsitante(id, visitanteRequeste);
        var informacoes = new VisitanteResponse(visitante);

        return ResponseEntity.ok(informacoes);
    }

    //Metodo que tras a listagem de todos os campos
    @GetMapping
    public ResponseEntity<Page<VisitanteResponse>> listarVizitante(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        var page = visitanteService.listarVisitante(pageable)
                .map(VisitanteResponse::new);

        return ResponseEntity.ok(page);
    }

    //Metodo de requisição para buscar dados de um visitante específico
    @GetMapping("/{id}")
    public ResponseEntity<VisitanteResponse> buscarInformacoesId(@PathVariable Long id) {

        Visitante visitante = visitanteService.vericarVisitanteId(id);
        var informacoesVisitante = new VisitanteResponse(visitante);

        return ResponseEntity.ok(informacoesVisitante);
    }

    //Metodo de requisição para excluir um visitante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> exclusaoVisitante(@PathVariable Long id) {

        visitanteService.deletarCadastosVisitantante(id);

        return ResponseEntity.noContent().build();
    }
}