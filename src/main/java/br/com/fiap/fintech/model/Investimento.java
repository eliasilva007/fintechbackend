package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Investimento {

    // Atributos
    private int idInvestimento; // Identificador único do investimento
    private String tipo;
    private double valorInvestimento;
    private double taxaRetornoInvestimento;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    // Construtor Padrão
    public Investimento() {}

    // Construtor Personalizado
    public Investimento(String tipo, double valorInvestimento, double taxaRetornoInvestimento, LocalDate dataInicio, LocalDate dataFim) {
        this.tipo = tipo;
        this.valorInvestimento = valorInvestimento;
        this.taxaRetornoInvestimento = taxaRetornoInvestimento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Getters e Setters
    public int getIdInvestimento() {
        return idInvestimento;
    }

    public void setIdInvestimento(int idInvestimento) {
        this.idInvestimento = idInvestimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

}
