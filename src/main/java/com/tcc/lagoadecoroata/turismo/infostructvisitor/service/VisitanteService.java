package com.tcc.lagoadecoroata.turismo.infostructvisitor.service;

import com.tcc.lagoadecoroata.turismo.infostructvisitor.dto.VisitanteAtulizacaoRequesteDto;
import com.tcc.lagoadecoroata.turismo.infostructvisitor.dto.VisitanteRequesteDto;
import com.tcc.lagoadecoroata.turismo.infostructvisitor.entity.Visitante;
import com.tcc.lagoadecoroata.turismo.infostructvisitor.reppository.VisitanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VisitanteService {
    private final VisitanteRepository visistanteRepository;

    public VisitanteService(VisitanteRepository visistanteRepository) {
        this.visistanteRepository = visistanteRepository;
    }

    //Metodo de cadastro de informações do usúario
    @Transactional
    public Visitante salvarCadastroVisitante(VisitanteRequesteDto visitanteRequeste) {

        if (visistanteRepository.existsByEmail(visitanteRequeste.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "E-mail já cadastrado.");
        }

        if (visistanteRepository.existsByTelefone(visitanteRequeste.telefone())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Telefone já cadastrado.");
        }

        Visitante visitante = new Visitante();
        visitante.setNome(visitanteRequeste.nome());
        visitante.setEmail(visitanteRequeste.email());
        visitante.setSenha(visitanteRequeste.senha());
        visitante.setTelefone(visitanteRequeste.telefone());

        return visistanteRepository.save(visitante);
    }

    //Metodo de atualização de informações do visitante
    @Transactional
    public Visitante dadosAtualizacaoVIsitante(Long id, VisitanteAtulizacaoRequesteDto visitanteRequeste) {

        Visitante visitante = visistanteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Visitante não encontrado."));

        if (visitanteRequeste.nome() != null) {
            visitante.setNome(visitanteRequeste.nome());
        }

        if (visitanteRequeste.senha() != null) {
            visitante.setSenha(visitanteRequeste.senha());
        }

        return visistanteRepository.save(visitante);
    }

    //Metodo de listagem de visitantes
    public Page<Visitante> listarVisitante(Pageable pageable) {
        return visistanteRepository.findAll(pageable);
    }

    public Visitante vericarVisitanteId(Long id) {
        return visistanteRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Visitante não encontrado"));
    }

    //Metodo de exclusao de cadastro de visitante
    @Transactional
    public void deletarCadastosVisitantante(Long id) {
        if (!visistanteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visitante não encontrado");
        }
        visistanteRepository.deleteById(id);
    }

}
