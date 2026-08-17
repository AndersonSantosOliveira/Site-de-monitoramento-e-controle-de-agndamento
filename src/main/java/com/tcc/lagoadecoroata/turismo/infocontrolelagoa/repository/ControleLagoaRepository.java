package com.tcc.lagoadecoroata.turismo.infocontrolelagoa.repository;

import com.tcc.lagoadecoroata.turismo.infocontrolelagoa.entity.ControleLagoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ControleLagoaRepository extends JpaRepository<ControleLagoa, Long> {
    Optional<ControleLagoa> findByData(LocalDate data);
}
