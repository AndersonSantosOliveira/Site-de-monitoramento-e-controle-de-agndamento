package com.tcc.lagoadecoroata.turismo.infostructfuncionario.entity;
import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = ("id"))
@Entity
@Table(name = "tbl_funcionario")

public class Fucionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Boolean ativo = true;
}
