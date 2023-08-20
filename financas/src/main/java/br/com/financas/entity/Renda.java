package br.com.financas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "renda")
@Getter
@Setter
public class Renda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "tipo_de_renda",length = 45)
    private String tipoRenda;
}
