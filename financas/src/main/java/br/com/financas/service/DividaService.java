package br.com.financas.service;

import br.com.financas.entity.Divida;
import br.com.financas.repository.DividaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DividaService {

    @Autowired
    private DividaRepository dividaRepository;

    public List<Divida> acharrDividaVencida() {
        return dividaRepository.findDividas(new Date());
    }

    public Divida salvar(Divida formulario) {
        return dividaRepository.save(formulario);
    }

    public Optional<Divida> acharId(Long idDivida) {
        return dividaRepository.findById(idDivida);
    }

    public Divida atualizar(Divida divida) {
        return dividaRepository.saveAndFlush(divida);
    }
}
