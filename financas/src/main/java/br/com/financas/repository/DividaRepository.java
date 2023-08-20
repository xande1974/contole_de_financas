package br.com.financas.repository;

import br.com.financas.entity.Divida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DividaRepository extends JpaRepository<Divida, Long> {
    @Query(value = "SELECT d.* FROM dividas d WHERE d.data_vencimento < ?1", nativeQuery = true)
    List<Divida> findDividas(Date dataAtual);
}
