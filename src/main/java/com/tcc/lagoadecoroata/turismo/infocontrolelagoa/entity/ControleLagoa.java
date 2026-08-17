package com.tcc.lagoadecoroata.turismo.infocontrolelagoa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "controle_lagoa")
public class ControleLagoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate data;
    @Column(nullable = false)
    private Integer ocupacaoAtual;
    @Column(nullable = false)
    private Integer totalCheckins;
    @Column(nullable = false)
    private Integer totalCheckouts;
}
