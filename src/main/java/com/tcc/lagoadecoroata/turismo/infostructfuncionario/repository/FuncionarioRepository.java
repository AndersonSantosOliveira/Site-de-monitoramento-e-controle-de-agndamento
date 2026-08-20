package com.tcc.lagoadecoroata.turismo.infostructfuncionario.repository;


import com.tcc.lagoadecoroata.turismo.infostructfuncionario.entity.Fucionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Fucionario, Long> {
    boolean existsByEmail(String email);
}
