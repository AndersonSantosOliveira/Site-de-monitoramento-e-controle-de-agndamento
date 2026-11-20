package com.tcc.lagoadecoroata.turismo.detailadministrativo.repository;

import com.tcc.lagoadecoroata.turismo.detailadministrativo.entity.RegistroLocal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistroLocalRepository extends JpaRepository<RegistroLocal, Long> {
    boolean existsByCodigoRegistro(String codigoRegistro);

    Optional<RegistroLocal> findByCodigoRegistro(String codigoRegistro);
}
