package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Divida {

    //Atributos

    private double valorTotal;
    private double valorPago;
    private LocalDate dataVencimento;
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
    public Divida(double valorTotal, double valorPago, LocalDate dataVencimento, String statusDivida, String tipoDivida, String descricaoDivida, int IdDivida, Conta conta) {
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.dataVencimento = dataVencimento;
        this.statusDivida = statusDivida;
        this.tipoDivida = tipoDivida;
        this.descricaoDivida = descricaoDivida;
        this.conta = conta;
        this.IdDivida = IdDivida;
    }

    //Método da classe
    public void situacaoDivida(){
        //Lógica que calcula a divida do usuário

        System.out.println("Essa é a situação da divida do usuario: " + statusDivida + " ---> Motivo da divida: " + descricaoDivida);
    }

}
