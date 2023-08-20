package br.com.financas.controller;

import br.com.financas.entity.*;
import br.com.financas.model.*;
import br.com.financas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/financas")
public class FinancasController {

    @Autowired
    private DividaService dividaService;

    @Autowired
    private GastoService gastoService;

    @Autowired
    private InvestimentoService investimentoService;

    @Autowired
    private RendaService rendaService;

    @Autowired
    private FinancasService financasService;

    @Autowired
    private TipoTransacaoService tipoTransacaoService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        String usuario = loginForm.getUsuario();
        String senha = loginForm.getSenha();

        // Lógica de autenticação
        if (usuario.equals("admin") && senha.equals("admin1234")) {
            // Login bem-sucedido
            return ResponseEntity.ok("Login bem-sucedido");
        } else {
            // Login falhou
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não permitido!");
        }
    }

    @PostMapping("/salvar-divida")
    public ResponseEntity<Financas> salvarDivida(@RequestBody DividaModel formulario) {
        Divida dividaSalva = salvarDividas(formulario);
        // vou salvar uma divida pra ver se ta dando erro, sobe como debug e coloca um breakpoint na linha
        TipoTransacao tipoTransacao = new TipoTransacao();
        tipoTransacao.setIdDivida(dividaSalva);
        tipoTransacao.setNome(formulario.getNome());
        TipoTransacao tipoTransacaoSalvo = tipoTransacaoService.salvar(tipoTransacao);
        // salvou a divida, ta com o id 252, vamo ver no banco
        Financas financas = new Financas();
        financas.setIdTipoTransacao(tipoTransacaoSalvo);
        financas.setValor(formulario.getValor());
        financas.setDataOperacao(new Date());

        return ResponseEntity.ok(financasService.salvar(financas));
    }

    private Divida salvarDividas(DividaModel formulario) {
        Divida divida = new Divida();
        divida.setDataVencimento(formulario.getDataVencimento());
        divida.setEmpresa(formulario.getEmpresa());

        return dividaService.salvar(divida);
    }

    @PostMapping("/salvar-gasto")
    public ResponseEntity<Financas> salvarGasto(@RequestBody GastoModel formulario) {

        Gasto gasto = new Gasto();
        gasto.setEspecificacaoGasto(formulario.getEspecificacaoGasto());
        Gasto gastoSalvo = gastoService.salvar(gasto);

        TipoTransacao tipoTransacao = new TipoTransacao();
        tipoTransacao.setIdGasto(gastoSalvo);
        tipoTransacao.setNome(formulario.getNome());
        TipoTransacao tipoTransacaoSalvo = tipoTransacaoService.salvar(tipoTransacao);

        Financas financas = new Financas();
        financas.setValor(formulario.getValor());
        financas.setDataOperacao(new Date());
        financas.setIdTipoTransacao(tipoTransacaoSalvo);

        return ResponseEntity.ok(financasService.salvar(financas));
    }

    @PostMapping("/salvar-investimento")
    public ResponseEntity<Financas> salvarInvestimento(@RequestBody InvestimentoModel formulario) {
        Investimento investimento = new Investimento();
        investimento.setDataInvestimento(formulario.getDataInvestimento());
        investimento.setTipoInvestimento(formulario.getTipoInvestimento());
        investimento.setInstituicao(formulario.getInstituicao());
        Investimento investimentoSalvo = investimentoService.salvar(investimento);


        TipoTransacao tipoTransacao = new TipoTransacao();
        tipoTransacao.setIdInvestimento(investimentoSalvo);
        tipoTransacao.setNome(formulario.getNome());
        TipoTransacao tipoTransacaoSalvo = tipoTransacaoService.salvar(tipoTransacao);

        Financas financas = new Financas();
        financas.setValor(formulario.getValor());
        financas.setDataOperacao(new Date());
        financas.setIdTipoTransacao(tipoTransacaoSalvo);

        return ResponseEntity.ok(financasService.salvar(financas));
    }

    @PostMapping("/salvar-renda")
    public ResponseEntity<Financas> salvarRenda(@RequestBody RendaModel formulario) {
        Renda rendaSalva = savarRenda(formulario);
        TipoTransacao tipoTransacaoSalvo = salvarTipoTransacao(formulario, rendaSalva);
        Financas financas = salvarFinancas(formulario, tipoTransacaoSalvo);
        return ResponseEntity.ok(financasService.salvar(financas));
    }

    private Renda savarRenda(RendaModel formulario) {
        try {
            Renda renda = new Renda();
            renda.setTipoRenda(formulario.getTipoRenda());
            Renda rendaSalva = rendaService.salvar(renda);
            return rendaSalva;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private TipoTransacao salvarTipoTransacao(RendaModel formulario, Renda rendaSalva) {
        try {
            TipoTransacao tipoTransacao = new TipoTransacao();
            tipoTransacao.setIdRenda(rendaSalva);
            tipoTransacao.setNome(formulario.getNome());
            TipoTransacao tipoTransacaoSalvo = tipoTransacaoService.salvar(tipoTransacao);
            return tipoTransacaoSalvo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Financas salvarFinancas(RendaModel formulario, TipoTransacao tipoTransacaoSalvo) {
        try {
            Financas financas = new Financas();
            financas.setDataOperacao(new Date());
            financas.setIdTipoTransacao(tipoTransacaoSalvo);
            financas.setValor(formulario.getValor());
            return financas;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Financas>> listar() {
        return ResponseEntity.ok(financasService.listar());
    }

    @PostMapping("/atualizar/divida/{id}")
    public ResponseEntity<Financas> atualizarDivida(@PathVariable("id") Long id, @RequestBody DividaModel dividaModel) {
        return ResponseEntity.ok(financasService.atualizarDivida(id, dividaModel));
    }

    @PostMapping("/atualizar/investimento/{id}")
    public ResponseEntity<Financas> atualizarInvestimento(@PathVariable("id") Long id, @RequestBody InvestimentoModel investimentoModel) {
        return ResponseEntity.ok(financasService.atualizarInvestimento(id, investimentoModel));
    }

    @PostMapping("/atualizar/renda/{id}")
    public ResponseEntity<Financas> atualizarRenda(@PathVariable("id") Long id, @RequestBody RendaModel rendaModel) {
        return ResponseEntity.ok(financasService.atualizarRenda(id, rendaModel));
    }

    @PostMapping("/atualizar/gasto/{id}")
    public ResponseEntity<Financas> atualizarGasto(@PathVariable("id") Long id, @RequestBody GastoModel gastoModel) {
        return ResponseEntity.ok(financasService.atualizarGasto(id, gastoModel));
    }

    @DeleteMapping("/deletar/financa/{id}/{transacao}")
    public Boolean deletarFinanca(@PathVariable("id") Long idFinanca,
                                  @PathVariable("transacao") Long idTransacao) {
        Financas financas = financasService.acharId(idFinanca);
        if (Objects.nonNull(financas)) {
            financasService.deletar(idFinanca, idTransacao);
            return true;
        }
        return false;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Financas> acharFinanca(@PathVariable("id") Long idFinaca) {
        return ResponseEntity.ok(financasService.acharId(idFinaca));
    }
}
