package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.exceptions.ContaNaoEncontradaException;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.service.ContaService;

import java.sql.SQLException;
import java.util.List;

public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    // Cadastrar uma nova conta
    public void cadastrarConta(Conta conta) {
        try {
            contaService.cadastrarConta(conta);
            System.out.println("Conta cadastrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar conta: " + e.getMessage());
        }
    }

    // Atualizar uma conta existente
    public void atualizarConta(Conta conta) {
        try {
            contaService.atualizarConta(conta);
            System.out.println("Conta atualizada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar conta: " + e.getMessage());
        }
    }

    // Buscar uma conta por ID
    public Conta buscarContaPorId(int id) {
        try {
            return contaService.buscarContaPorId(id);
        } catch (ContaNaoEncontradaException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao buscar conta: " + e.getMessage());
        }
        return null;
    }

    // Listar todas as contas
    public List<Conta> listarTodasContas() {
        try {
            return contaService.listarTodasContas();
        } catch (SQLException e) {
            System.err.println("Erro ao listar contas: " + e.getMessage());
        }
        return null;
    }

    // Deletar uma conta pelo ID
    public void deletarConta(int id) {
        try {
            contaService.deletarConta(id);
            System.out.println("Conta deletada com sucesso!");
        } catch (ContaNaoEncontradaException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao deletar conta: " + e.getMessage());
        }
    }

    // Realizar login na conta
    public Conta login(String identificador, String senha) {
        try {
            return contaService.login(identificador, senha);
        } catch (Exception e) {
            System.err.println("Erro ao fazer login: " + e.getMessage());
        }
        return null;
    }
}
