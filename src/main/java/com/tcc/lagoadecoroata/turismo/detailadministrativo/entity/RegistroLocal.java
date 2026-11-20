package com.tcc.lagoadecoroata.turismo.detailadministrativo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "registro_local")
public class RegistroLocal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nomeResponsavel;
    @Column(nullable = false, unique = true)
    private String codigoRegistro;
    @Column(nullable = false)
    private Integer quantidadePessoas;
    @Column(nullable = false)
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAndRegistro status;
}
