package br.com.financas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "investimento")
@Getter
@Setter
public class Investimento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "tipo_de_investimento", length = 45)
    private String tipoInvestimento;

    @Column(name = "data_do_investimento")
    private Date dataInvestimento;

    @Column(name = "instituicao", length = 45)
    private String instituicao;
}
