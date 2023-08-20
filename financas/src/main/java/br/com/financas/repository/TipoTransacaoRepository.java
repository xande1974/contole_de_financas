package br.com.financas.repository;

import br.com.financas.entity.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTransacaoRepository extends JpaRepository <TipoTransacao, Long>{

    @Modifying
    @Query(value = "delete from tipo_de_transacao t where t.id = :idTransacao", nativeQuery = true)
    void deleteTransacaoById(@Param("idTransacao") Long idTransacao);
}
