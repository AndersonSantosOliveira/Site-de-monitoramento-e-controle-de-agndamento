package com.tcc.lagoadecoroata.turismo.infostructuser.controller;

import com.tcc.lagoadecoroata.turismo.infostructuser.dto.FuncionarioAtualizarRequestDto;
import com.tcc.lagoadecoroata.turismo.infostructuser.dto.FuncionarioRequestDto;
import com.tcc.lagoadecoroata.turismo.infostructuser.dto.FuncionarioResponseDto;
import com.tcc.lagoadecoroata.turismo.infostructuser.entity.Fucionario;
import com.tcc.lagoadecoroata.turismo.infostructuser.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDto> cadastroFuncionario(
            @RequestBody @Valid FuncionarioRequestDto funcionarioRequestDto, UriComponentsBuilder uriBuilder) {
        var funcionario = funcionarioService.salvarCadastroFuncionario(funcionarioRequestDto);
        var uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).body(new FuncionarioResponseDto(funcionario));
    }


    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDto> atualizarCadastroFuncionario(
            @RequestBody @Valid FuncionarioAtualizarRequestDto dto,
            @PathVariable Long id) {
        var funcionario = funcionarioService.atualizarInformacoesFucionario(dto, id);

        return ResponseEntity.ok(new FuncionarioResponseDto(funcionario));
    }


    @GetMapping
    public ResponseEntity<Page<FuncionarioResponseDto>> listagemFucionarios(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        var page = funcionarioService.listarFuncionario(pageable).map(FuncionarioResponseDto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDto> buscarFuncionarioId(@PathVariable Long id) {
        Fucionario funcionario = funcionarioService.buscarFunciarioPorId(id);
        var informacoesFuncionario = new FuncionarioResponseDto(funcionario);
        return ResponseEntity.ok(informacoesFuncionario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}
