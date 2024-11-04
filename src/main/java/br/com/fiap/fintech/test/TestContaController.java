// TestContaController.java
package br.com.fiap.fintech.test;

import br.com.fiap.fintech.controller.ContaController;
import br.com.fiap.fintech.model.ContaFisica;
import br.com.fiap.fintech.repository.ContaRepository;
import br.com.fiap.fintech.service.ContaService;

public class TestContaController {

    public static void main(String[] args) {
        // Inicialização do repository e service para o controller
        ContaRepository contaRepository = new ContaRepository();
        ContaService contaService = new ContaService(contaRepository);
        ContaController contaController = new ContaController(contaService);

        // Testar cadastro de nova conta
        ContaFisica novaConta = new ContaFisica();
        novaConta.setNome("Carlos Souza");
        novaConta.setCpf("12345678901");
        novaConta.setEmail("carlos.souza@example.com");

        System.out.println("=== Teste de Cadastro de Conta ===");
        contaController.cadastrarConta(novaConta);

        // Testar busca de conta por ID
        System.out.println("=== Teste de Busca de Conta ===");
        var contaBuscada = contaController.buscarContaPorId(novaConta.getId());
        if (contaBuscada != null) {
            System.out.println("Conta encontrada: " + contaBuscada.getNome());
        } else {
            System.out.println("Conta não encontrada.");
        }

        // Testar atualização de conta
        System.out.println("=== Teste de Atualização de Conta ===");
        novaConta.setEmail("novo.email@example.com");
        contaController.atualizarConta(novaConta);

        // Testar listagem de contas
        System.out.println("=== Teste de Listagem de Contas ===");
        var contas = contaController.listarTodasContas();
        if (contas != null && !contas.isEmpty()) {
            contas.forEach(conta -> System.out.println("Conta: " + conta.getNome()));
        } else {
            System.out.println("Nenhuma conta encontrada.");
        }

        // Testar login
        System.out.println("=== Teste de Login ===");
        var contaLogada = contaController.login("12345678901", "senha123");
        if (contaLogada != null) {
            System.out.println("Login bem-sucedido para: " + contaLogada.getNome());
        } else {
            System.out.println("Falha no login. Identificador ou senha incorretos.");
        }

        // Testar exclusão de conta
        System.out.println("=== Teste de Exclusão de Conta ===");
        contaController.deletarConta(novaConta.getId());
        System.out.println("Conta excluída com sucesso!");
    }
}
