package br.com.fiap.fintech.interfaceDAO;

import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.tipoenum.TipoConta;

import java.sql.SQLException;
import java.util.List;

public interface ContaDAO {

    // Método para cadastrar uma nova conta
    void cadastrar(Conta conta) throws SQLException;

    // Método para atualizar uma conta existente
    void atualizar(Conta conta) throws SQLException;

    // Método para deletar uma conta
    void deletar(int idUsuario) throws SQLException;

    // Método para buscar uma conta pelo ID
    Conta buscarPorId(int idUsuario) throws SQLException;

    // Método para login do usuário usando CPF ou CNPJ e senha
    Conta fazerLogin(String identificador, String senha) throws SQLException;

    // Método para listar todas as contas (pode ser útil para fins administrativos)
    List<Conta> listarTodas() throws SQLException;

}

