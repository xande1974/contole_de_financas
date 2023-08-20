package br.com.financas.repository;

import br.com.financas.entity.Renda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendaRepository extends JpaRepository<Renda, Long> {
}
