package br.com.financas.repository;


import br.com.financas.entity.Financas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FinancasRepository extends JpaRepository<Financas, Long> {

    @Modifying
    @Query(value = "delete from financas f where f.id = :idFinanca", nativeQuery = true)
    void deleteFinancaById(@Param("idFinanca") Long idFinanca);
}
