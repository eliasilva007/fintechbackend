package br.com.fiap.fintech.interfaceDAO;

import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.tipoenum.TipoConta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ContaDAO {

        void cadastrar(Connection connection, Conta conta) throws SQLException;

        void atualizar(Connection connection, Conta conta) throws SQLException;
        void deletar(Connection connection, int idUsuario) throws SQLException;
        Conta buscarPorId(Connection connection, int idUsuario) throws SQLException;
        Conta fazerLogin(Connection connection, String identificador, String senha) throws SQLException;
        List<Conta> listarTodas(Connection connection) throws SQLException;



}

