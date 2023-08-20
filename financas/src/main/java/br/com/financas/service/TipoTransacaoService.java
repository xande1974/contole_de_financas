package br.com.financas.service;

import br.com.financas.entity.TipoTransacao;
import br.com.financas.repository.TipoTransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoTransacaoService {

    @Autowired
    private TipoTransacaoRepository tipoTransacaoRepository;

    public TipoTransacao salvar(TipoTransacao tipoTransacao) {
        return tipoTransacaoRepository.save(tipoTransacao);
    }
}
