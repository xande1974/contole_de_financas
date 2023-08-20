package br.com.financas.service;

import br.com.financas.entity.Investimento;
import br.com.financas.repository.InvestimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvestimentoService {

    @Autowired
    private InvestimentoRepository investimentoRepository;

    public Investimento salvar(Investimento investimento) {
        return investimentoRepository.save(investimento);
    }

    public Optional<Investimento> pegarId(Long idInvestimento) {
        return investimentoRepository.findById(idInvestimento);
    }

    public Investimento atualizarInvestimento(Investimento investimento) {
        return investimentoRepository.saveAndFlush(investimento);
    }
}
