package br.com.fiap.fintech.model;

public class ContaJuridica extends Conta {

    //Atributos

    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;

    //getters e setters

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    //Construtores

    public ContaJuridica(){

    }

    public ContaJuridica(String nome, String email, String numeroTelefone, String senha, String cnpj, String razaoSocial, String nomeFantasia, String inscricaoEstadual) {
        super(nome, email, numeroTelefone, senha);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    //Método adaptado para ContaJuridica

    @Override
    public void fazerRegistro() {
        //Lógica para fazer o registro

        System.out.printf("\nUsuário %s, CNPJ n° %s, cadastrado com Sucesso! ", nomeFantasia,cnpj);

    }

    @Override
    public void fazerLogin() {
        System.out.println("Usuario Jurídico logado com Sucesso! CNPJ: " + getCnpj());    }
}
