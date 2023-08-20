package br.com.financas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "financas")
@Getter
@Setter
public class Financas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data_da_operacao")
    private Date dataOperacao;

    @ManyToOne
    @JoinColumn(name = "id_tipo_de_transacao", foreignKey = @ForeignKey(name = "fk_financas_idTipoTransacao"))
    private TipoTransacao idTipoTransacao;
}
