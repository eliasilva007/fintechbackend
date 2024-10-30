package br.com.fiap.fintech.account;

public abstract class Conta {

    //Atributos

    protected String nome;
    protected String email;
    protected String numeroTelefone;
    protected String senha;

    //Getters e Setters da classe:

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    //Construtor Padrão

    public Conta(){

    }

    //Construtor Personalizado

    public Conta(String nome, String email, String numeroTelefone, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.numeroTelefone = numeroTelefone;


    }

    //Métodos da classe
    public void fazerRegistro() {
        //Lógica para realizar o registro do novo usuário

        System.out.println("Usuário Cadastrado com Sucesso: " + getNome());


    }
    public void fazerLogin(){
        //Lógica para realizar o Login

        System.out.println("Usuário Logado com Sucesso: "+ getNome());
    }

}
