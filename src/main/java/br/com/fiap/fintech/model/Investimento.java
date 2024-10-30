package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Investimento {

    //Atributos

    private String descricaoInvestimento;
    private double valorInvestimento;
    private double taxaRetornoInvestimento;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Conta conta;

    //Getters e Setters

    public String getDescricaoInvestimento() {
        return descricaoInvestimento;
    }

    public void setDescricaoInvestimento(String descricaoInvestimento) {
        this.descricaoInvestimento = descricaoInvestimento;
    }

    public double getValorInvestimento() {
        return valorInvestimento;
    }

    public void setValorInvestimento(double valorInvestimento) {
        this.valorInvestimento = valorInvestimento;
    }

    public double getTaxaRetornoInvestimento() {
        return taxaRetornoInvestimento;
    }

    public void setTaxaRetornoInvestimento(double taxaRetornoInvestimento) {
        this.taxaRetornoInvestimento = taxaRetornoInvestimento;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    //Contrutor Padrão
    public Investimento() {
    }

    //Construtor Personalizado
    public Investimento(String descricaoInvestimento, double valorInvestimento, double taxaRetornoInvestimento, LocalDate dataInicio, LocalDate dataFim, Conta conta) {
        this.descricaoInvestimento = descricaoInvestimento;
        this.valorInvestimento = valorInvestimento;
        this.taxaRetornoInvestimento = taxaRetornoInvestimento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.conta = conta;
    }

    //Método da classe

    public void fazerInvestimento(){
        //Logica para realizar investimento


        System.out.println("Investimento Realizado com Sucesso: " + descricaoInvestimento);
    }
}
