package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Meta {

    //Atributos

    private double valorMeta;
    private String descricaoMeta;
    private LocalDate dataLimite;
    private Conta conta;

    //Getters e Settes


    public double getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(double valorMeta) {
        this.valorMeta = valorMeta;
    }

    public String getDescricaoMeta() {
        return descricaoMeta;
    }

    public void setDescricaoMeta(String descricaoMeta) {
        this.descricaoMeta = descricaoMeta;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }


    //Construtor Padrão
    public Meta(){

    }

    //Construtor Personalizado
    public Meta(double valorMeta, String descricaoMeta, LocalDate dataLimite, Conta conta){
        this.valorMeta = valorMeta;
        this.descricaoMeta = descricaoMeta;
        this.dataLimite = dataLimite;
        this.conta = conta;

    }

    //Método da classe
    public void metaRegistrada(){
        //Lógica que reigstra a br.com.fiap.fintech.model.Meta do Usuário

        System.out.println("Meta Registrada com Sucesso: " + descricaoMeta);
    }

}
