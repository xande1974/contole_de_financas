package br.com.financas.service;

import br.com.financas.entity.Renda;
import br.com.financas.repository.RendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RendaService {

    @Autowired
    private RendaRepository rendaRepository;

    public Renda salvar(Renda renda) {
        return rendaRepository.save(renda);
    }
}
