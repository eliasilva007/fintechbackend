package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Divida {

    //Atributos

    private double valorTotal;
    private double valorPago;
    private LocalDate dataVencimento;
    private LocalDate dataCriacao;
    private String statusDivida;
    private String tipoDivida;
    private String descricaoDivida;
    private Conta conta;
    private int IdDivida;

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getStatusDivida() {
        return statusDivida;
    }

    public void setStatusDivida(String statusDivida) {
        this.statusDivida = statusDivida;
    }

    public String getTipoDivida() {
        return tipoDivida;
    }

    public void setTipoDivida(String tipoDivida) {
        this.tipoDivida = tipoDivida;
    }

    public String getDescricaoDivida() {
        return descricaoDivida;
    }

    public void setDescricaoDivida(String descricaoDivida) {
        this.descricaoDivida = descricaoDivida;
    }

    public int getIdDivida() {
        return IdDivida;
    }

    public void setIdDivida(int idDivida) {
        IdDivida = idDivida;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    //Construtor padrão
    public Divida() {
    }

    //Construtor Personalizado
    public Divida(double valorTotal, double valorPago, LocalDate dataVencimento, LocalDate dataCriacao, String statusDivida, String tipoDivida, String descricaoDivida, int IdDivida, Conta conta) {
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.dataVencimento = dataVencimento;
        this.dataCriacao = dataCriacao;
        this.statusDivida = statusDivida;
        this.tipoDivida = tipoDivida;
        this.descricaoDivida = descricaoDivida;
        this.IdDivida = IdDivida;
        this.conta = conta;
    }

    //Construtor teste
    public Divida(double valorTotal, double valorPago, LocalDate dataVencimento, LocalDate dataCriacao, String statusDivida, String tipoDivida, String descricaoDivida, int IdDivida) {
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.dataVencimento = dataVencimento;
        this.dataCriacao = dataCriacao;
        this.statusDivida = statusDivida;
        this.tipoDivida = tipoDivida;
        this.descricaoDivida = descricaoDivida;
        this.IdDivida = IdDivida;
    }

}
