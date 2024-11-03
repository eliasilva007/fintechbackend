package br.com.fiap.fintech.model;
import br.com.fiap.fintech.tipoenum.TipoConta;

public abstract class Conta {

    //Atributos

    protected int id;
    protected String nome;
    protected String email;
    protected String numeroTelefone;
    protected String senha;
    protected TipoConta tipoConta;

    //Getters e Setters da classe:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    //Construtor Padrão

    public Conta(){

    }

    //Construtor Personalizado

    public Conta(int id, String nome, String email, String numeroTelefone, String senha, TipoConta tipoConta){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.numeroTelefone = numeroTelefone;
        this.senha = senha;
        this.tipoConta = tipoConta;

    }

}
