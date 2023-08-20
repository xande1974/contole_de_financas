package br.com.financas.service;

import br.com.financas.entity.Gasto;
import br.com.financas.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;


    public Gasto salvar(Gasto gasto) {
        return gastoRepository.save(gasto);
    }
}
