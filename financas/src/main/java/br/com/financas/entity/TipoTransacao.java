package br.com.financas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipo_de_transacao")
@Getter
@Setter
public class TipoTransacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nome", length = 45)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "idRenda", foreignKey = @ForeignKey(name = "fk_tipo_de_transacao_renda"))
    private Renda idRenda;

    @ManyToOne
    @JoinColumn(name = "idInvestimento", foreignKey = @ForeignKey(name = "fk_tipo_de_transacao_investimento") )
    private Investimento idInvestimento;

    @ManyToOne
    @JoinColumn(name = "idDividas", foreignKey = @ForeignKey(name = "fk_tipo_de_transacao_dividas") )
    private Divida idDivida;

    @ManyToOne
    @JoinColumn(name = "idGastos", foreignKey = @ForeignKey(name = "fk_tipo_de_transacao_gasto") )
    private Gasto idGasto;

}
