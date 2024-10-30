package br.com.fiap.fintech.account;

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

    public ContaFisica() {
    }

    public ContaFisica(String nome, String email, String numeroTelefone, String senha, LocalDate dataNascimento, String cpf, String rg) {
        super(nome, email, numeroTelefone, senha);
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
    }

    //Método adaptado para ContaFisica

    @Override
    public void fazerRegistro() {
        //Lógica para fazer registro

        System.out.printf("\nUsuário %s, Rg n° %s, cadastrado com Sucesso! ", nome,rg);
    }

    @Override
    public void fazerLogin() {
        //Lógica para realizar o Login

        System.out.println("Usuario Físico logado com Sucesso! CPF: " + getCpf());
    }
}
