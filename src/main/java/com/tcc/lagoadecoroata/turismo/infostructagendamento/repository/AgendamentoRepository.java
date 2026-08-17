package com.tcc.lagoadecoroata.turismo.infostructagendamento.repository;

import com.tcc.lagoadecoroata.turismo.infostructagendamento.entity.Agendamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("""
            SELECT a
            FROM Agendamento a
            JOIN FETCH a.visitante
            WHERE a.id = :id
            """)
    Optional<Agendamento> findByIdComVisitante(@Param("id") Long id);

    boolean existsByVisitanteIdAndData(Long visitanteId, LocalDate data);

    boolean existsByCodigoReserva(String codigoReserva);

    @Query("""
                SELECT COALESCE(SUM(a.quantidadePessoas), 0)
                FROM Agendamento a
                WHERE a.data = :data
                  AND a.status = 'CONFIRMADO'
            """)
    Integer obterTotalPessoasAgendadas(@Param("data") LocalDate data);

    @Query("""
            SELECT a
            FROM Agendamento a
            JOIN FETCH a.visitante
            WHERE a.data = :data
            """)
    List<Agendamento> findByData(LocalDate data);


    @Query("SELECT COALESCE(SUM(a.quantidadePessoas), 0) FROM Agendamento a " +
            "WHERE a.data = :data AND a.status <> 'CANCELADO'")
    Integer somarPessoasNaData(@Param("data") LocalDate data);


    @Query(
            value = """
                    SELECT a
                    FROM Agendamento a
                    JOIN FETCH a.visitante
                    """,
            countQuery = """
                    SELECT COUNT(a)
                    FROM Agendamento a
                    """
    )
    Page<Agendamento> findAllComVisitante(Pageable pageable);

    @Query("""
                SELECT a
                FROM Agendamento a
                JOIN FETCH a.visitante
                WHERE a.codigoReserva = :codigo
            """)
    Optional<Agendamento> findByCodigoReserva(@Param("codigo") String codigo);

}
