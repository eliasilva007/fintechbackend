package br.com.fiap.fintech.interfaceDAO;

import br.com.fiap.fintech.model.Conta;

import java.sql.SQLException;
import java.util.List;

public interface ContaDAO {

        void cadastrar(Conta conta) throws SQLException;

        void atualizar(Conta conta) throws SQLException;
        void deletarConta(int idUsuario) throws SQLException;
        Conta buscarPorId(int idUsuario) throws SQLException;
        Conta fazerLogin(String identificador, String senha) throws SQLException;
        List<Conta> listarTodasContas() throws SQLException;



}

