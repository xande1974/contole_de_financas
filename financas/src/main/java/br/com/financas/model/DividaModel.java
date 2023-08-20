package br.com.financas.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DividaModel {

    private Long id;
    private String nome;
    private Double valor;
    private String empresa;
    private Date dataVencimento;
}
