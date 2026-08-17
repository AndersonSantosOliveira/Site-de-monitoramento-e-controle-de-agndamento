package com.tcc.lagoadecoroata.turismo.infostructvisitor.reppository;

import com.tcc.lagoadecoroata.turismo.infostructvisitor.entity.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitanteRepository extends JpaRepository<Visitante, Long> {
    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);
}
