package br.com.financas.service;

import br.com.financas.entity.Financas;
import br.com.financas.model.DividaModel;
import br.com.financas.model.GastoModel;
import br.com.financas.model.InvestimentoModel;
import br.com.financas.model.RendaModel;
import br.com.financas.repository.FinancasRepository;
import br.com.financas.repository.TipoTransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FinancasService {

    @Autowired
    private FinancasRepository financasRepository;

    @Autowired
    private TipoTransacaoRepository tipoTransacaoRepository;

    public Financas salvar(Financas financas) {
        return financasRepository.save(financas);
    }

    public List<Financas> listar() {
        return financasRepository.findAll();
    }

    public Financas acharId(Long idFinanca) {
        return financasRepository.findById(idFinanca).get();
    }

    @Transactional
    public void deletar(Long idFinanca, Long idTransacao) {
        financasRepository.deleteFinancaById(idFinanca);
        tipoTransacaoRepository.deleteTransacaoById(idTransacao);
    }

    public Financas atualizarRenda(Long id, RendaModel rendaModel) {
        Financas financa = financasRepository.findById(id).get();
        financa.setValor(rendaModel.getValor());
        financa.getIdTipoTransacao().setNome(rendaModel.getNome());
        financa.getIdTipoTransacao().getIdRenda().setTipoRenda(rendaModel.getTipoRenda());
        financa.setDataOperacao(new Date());
        return financasRepository.saveAndFlush(financa);
    }

    public Financas atualizarDivida(Long id, DividaModel dividaModel) {
        Financas financa = financasRepository.findById(id).get();
        financa.setValor(dividaModel.getValor());
        financa.getIdTipoTransacao().setNome(dividaModel.getNome());
        financa.getIdTipoTransacao().getIdDivida().setDataVencimento(dividaModel.getDataVencimento());
        financa.getIdTipoTransacao().getIdDivida().setEmpresa(dividaModel.getEmpresa());
        financa.setDataOperacao(new Date());
        return financasRepository.saveAndFlush(financa);
    }

    public Financas atualizarInvestimento(Long id, InvestimentoModel investimentoModel) {
        Financas financa = financasRepository.findById(id).get();
        financa.setValor(investimentoModel.getValor());
        financa.getIdTipoTransacao().setNome(investimentoModel.getNome());
        financa.getIdTipoTransacao().getIdInvestimento().setInstituicao(investimentoModel.getInstituicao());
        financa.getIdTipoTransacao().getIdInvestimento().setDataInvestimento(investimentoModel.getDataInvestimento());
        financa.getIdTipoTransacao().getIdInvestimento().setTipoInvestimento(investimentoModel.getTipoInvestimento());
        financa.setDataOperacao(new Date());
        return financasRepository.saveAndFlush(financa);
    }

    public Financas atualizarGasto(Long id, GastoModel gastoModel) {
        Financas financa = financasRepository.findById(id).get();
        financa.setValor(gastoModel.getValor());
        financa.getIdTipoTransacao().setNome(gastoModel.getNome());
        financa.getIdTipoTransacao().getIdGasto().setEspecificacaoGasto(gastoModel.getEspecificacaoGasto());
        financa.setDataOperacao(new Date());
        return financasRepository.saveAndFlush(financa);
    }
}
