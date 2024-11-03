package br.com.fiap.fintech.model;

import br.com.fiap.fintech.tipoenum.TipoTransacao;

import java.time.LocalDate;

///
// Futuramente será implantado mais lógica e métodos para identificar e associar corretamente
// cada transação com sua conta, exibir historico etc...
///

public class Transacao {

    //Atributos

    private int id;
    private double valor;
    private String formaPagamento;
    private LocalDate data;
    private Conta conta;
    private TipoTransacao tipoTransacao;
    private String descricao;

    //Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Construtores

    public Transacao(){

    }

    public Transacao(int id, double valor, String formaPagamento, LocalDate data, TipoTransacao tipoTransacao, String descricao, Conta conta){
        this.id = id;
        this.formaPagamento = formaPagamento;
        this.data = data;
        this.tipoTransacao = tipoTransacao;
        this.descricao = descricao;
        this.conta = conta;
    }

    //Método da classe

    public void exibirDetalhes(){
        System.out.printf("""
                Descrição da Transação: %s
                Valor da Transação: %.2f
                Data: %s
                """, getDescricao(), getValor(), getData().toString());

    }
}
