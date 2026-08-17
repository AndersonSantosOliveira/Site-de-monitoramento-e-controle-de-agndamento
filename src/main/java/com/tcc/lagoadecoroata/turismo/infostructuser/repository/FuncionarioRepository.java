package com.tcc.lagoadecoroata.turismo.infostructuser.repository;


import com.tcc.lagoadecoroata.turismo.infostructuser.entity.Fucionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Fucionario, Long> {
    boolean existsByEmail(String email);
}
