package br.com.fiap.fintech.functions;
import br.com.fiap.fintech.account.ContaFisica;
import br.com.fiap.fintech.account.ContaJuridica;
import java.time.LocalDate;

//Apenas usamos a Classe Main para fazer o teste das outras classes...

public class Main {
    public static void main(String[] args) {

        //Testando Resgistrando e Logando na conta Física!
        testarContaFisica();

        System.out.println("--------------------------------------------");

        //Testando Resgistrando e Logando na conta Jurídica!

        testarContaJuridica();

        System.out.println("--------------------------------------------");

        //Testando a classe que registra transações

        testarTransacao();

        System.out.println("--------------------------------------------");

        //Testando a Classe Divida e seu método
        testarDivida();

        System.out.println("--------------------------------------------");

        //testando a calsse Meta e o seu método
        testarMeta();

        System.out.println("--------------------------------------------");

        //testando a classe Investimento e o Método
        testarInvestimento();

        System.out.println("--------------------------------------------");

    }

    //Funções que instanciam os objetos e chamam os metodos para testar as calasses!

    public static void testarContaFisica() {
        System.out.println("Registrando sua conta, por favor aguarde...");
        ContaFisica usuariofisico = new ContaFisica();
        usuariofisico.setCpf("123.456.789-10");
        usuariofisico.setNome("Elias José");
        usuariofisico.setRg("19216801");
        usuariofisico.fazerRegistro();
        System.out.println("\nRealizando Login na sua nova conta, aguarde...");
        usuariofisico.fazerLogin();
    }

    private static void testarContaJuridica(){
        System.out.println("Realizando Registro da sua conta Juridica, aguarde!");
        ContaJuridica usuarioJuridico = new ContaJuridica();
        usuarioJuridico.setNomeFantasia("Empresa X");
        usuarioJuridico.setCnpj("12.345.678/0001-00");
        usuarioJuridico.fazerRegistro();
        System.out.println("\nRealizando Login na sua conta...");
        usuarioJuridico.fazerLogin();
    }

    private static void testarTransacao(){
        System.out.println("Buscando detalhes da sua transação... Aguarde!");
        Transacao transacao1 = new Transacao();
        transacao1.setDescricao("Recebimento do Salário");
        transacao1.setValor(1412);
        transacao1.setData(LocalDate.of(2024,9,5));
        transacao1.exibirDetalhes();
    }

    private static void testarDivida(){
        Divida divida1 = new Divida();
        divida1.setDescricaoDivida("Ar condicionado novo");
        divida1.setStatusDivida("2 Parcelas pagas de 8");
        divida1.setValorTotal(2.800);
        divida1.setValorPago(700);
        divida1.setDataVencimento(LocalDate.of(2025, 4, 10));
        divida1.situacaoDivida();
    }

    private static void testarMeta(){
        Meta meta1 = new Meta();
        meta1.setDescricaoMeta("Comprar uma Moto");
        meta1.setValorMeta(26.400);
        meta1.setDataLimite(LocalDate.of(2024, 10,10));
        meta1.metaRegistrada();
    }
    private static void testarInvestimento(){
        Investimento investimento1 = new Investimento();
        investimento1.setDescricaoInvestimento("Casa de Aluguel, 3k de retorno ao mês");
        investimento1.setDataInicio(LocalDate.of(2024, 8, 26));
        investimento1.setValorInvestimento(132.630);
        investimento1.setTaxaRetornoInvestimento(3.000);
        investimento1.fazerInvestimento();
    }
}