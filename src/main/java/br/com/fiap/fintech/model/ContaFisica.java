package br.com.fiap.fintech.model;

import br.com.fiap.fintech.tipoenum.TipoConta;

import java.time.LocalDate;

public class ContaFisica extends Conta {

    // atributos

    private String cpf;
    private String rg;
    private LocalDate dataNascimento;

    // getters e setters

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {

        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    // construtores

    public ContaFisica(String nome, String email, String number, String senha, LocalDate localDate, String id, String s) {
    }

    public ContaFisica(int id, String nome, String email, String numeroTelefone, String senha, LocalDate dataNascimento, String cpf, String rg) {
        super(id, nome, email, numeroTelefone, senha, TipoConta.FISICA);
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
    }

}
