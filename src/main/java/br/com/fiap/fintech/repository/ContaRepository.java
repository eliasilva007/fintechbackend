package br.com.fiap.fintech.repository;

import br.com.fiap.fintech.interfaceDAO.ContaDAO;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaRepository {
    private final ContaDAO contaDAO;
    private static final Logger logger = Logger.getLogger(ContaRepository.class.getName());

    public ContaRepository(ContaDAO contaDAO) {
        this.contaDAO = contaDAO;
    }

    // Método para cadastrar uma nova conta
    public void cadastrarConta(Conta conta) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            contaDAO.cadastrar(conta);
            logger.info("Conta cadastrada com sucesso: " + conta);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao cadastrar a conta: " + e.getMessage(), e);
            throw e;
        }
    }

    // Método para atualizar uma conta existente
    public void atualizarConta(Conta conta) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            contaDAO.atualizar(conta);
            logger.info("Conta atualizada com sucesso: " + conta);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar a conta: " + e.getMessage(), e);
            throw e;
        }
    }

    // Método para deletar uma conta
    public void deletarConta(int idUsuario) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            contaDAO.deletar(idUsuario);
            logger.info("Conta deletada com sucesso para o ID: " + idUsuario);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao deletar a conta: " + e.getMessage(), e);
            throw e;
        }
    }

    // Método para buscar uma conta pelo ID
    public Conta buscarContaPorId(int idUsuario) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            Conta conta = contaDAO.buscarPorId(idUsuario);
            logger.info("Conta buscada com sucesso para o ID: " + idUsuario);
            return conta;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar a conta: " + e.getMessage(), e);
            throw e;
        }
    }

    // Método para login do usuário
    public Conta fazerLogin(String identificador, String senha) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            Conta conta = contaDAO.fazerLogin(identificador, senha);
            logger.info("Login realizado com sucesso para o identificador: " + identificador);
            return conta;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao fazer login: " + e.getMessage(), e);
            throw e;
        }
    }

    // Método para listar todas as contas
    public List<Conta> listarTodasContas() throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            List<Conta> contas = contaDAO.listarTodas();
            logger.info("Listagem de todas as contas realizada com sucesso.");
            return contas;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar todas as contas: " + e.getMessage(), e);
            throw e;
        }
    }
}