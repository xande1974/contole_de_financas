package br.com.financas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "divida")
@Getter
@Setter
public class Divida {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "empresa", length = 45)
    private String empresa;

    @Column(name = "data_vencimento")
    private Date dataVencimento;
}
