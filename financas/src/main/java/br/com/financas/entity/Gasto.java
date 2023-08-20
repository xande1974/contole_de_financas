package br.com.financas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="gasto")
@Getter
@Setter
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="especificacao_de_gasto", length = 45)
    private String especificacaoGasto;
}
