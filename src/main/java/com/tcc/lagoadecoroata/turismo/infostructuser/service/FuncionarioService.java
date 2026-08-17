package com.tcc.lagoadecoroata.turismo.infostructuser.service;

import com.tcc.lagoadecoroata.turismo.infostructuser.dto.FuncionarioAtualizarRequestDto;
import com.tcc.lagoadecoroata.turismo.infostructuser.dto.FuncionarioRequestDto;
import com.tcc.lagoadecoroata.turismo.infostructuser.entity.Fucionario;
import com.tcc.lagoadecoroata.turismo.infostructuser.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;


    public FuncionarioService(FuncionarioRepository repository) {
        this.funcionarioRepository = repository;
    }

    //criando metodo de salvar funcionario
    @Transactional
    public Fucionario salvarCadastroFuncionario(FuncionarioRequestDto funcionarioRequestDto) {
        if (funcionarioRepository.existsByEmail(funcionarioRequestDto.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "E-mail já cadastrado.");
        }

        Fucionario funcionario = new Fucionario();
        funcionario.setNome(funcionarioRequestDto.nome());
        funcionario.setEmail(funcionarioRequestDto.email());
        funcionario.setSenha(funcionarioRequestDto.senha());

        return funcionarioRepository.save(funcionario);
    }

    //criando o metodo de atualização
    @Transactional
    public Fucionario atualizarInformacoesFucionario(FuncionarioAtualizarRequestDto dto, Long id) {

        Fucionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Funcionário não encontrado."));

        if (dto.nome() != null) {
            funcionario.setNome(dto.nome());
        }

        if (dto.email() != null
                && funcionarioRepository.existsByEmail(dto.email())
                && !funcionario.getEmail().equals(dto.email())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "E-mail já cadastrado.");
        }

        if (dto.email() != null) {
            funcionario.setEmail(dto.email());
        }

        if (dto.senha() != null) {
            funcionario.setSenha(dto.senha());
        }

        return funcionarioRepository.save(funcionario);
    }

    //listagem de funcionarios
    public Page<Fucionario> listarFuncionario(Pageable pageable) {
        return funcionarioRepository.findAll(pageable);
    }

    //buscar funcionario especifico via id
    public Fucionario buscarFunciarioPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Funcionário não encontrado"));
    }

    //exclusão de funcionario via id
    @Transactional
    public void deletarFuncionario(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcioário não encontrado");
        }
        funcionarioRepository.deleteById(id);
    }


}


