package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.exceptions.DatabaseConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USUARIO = "rm558943";
    private static final String SENHA = "100603";

    // Método para criar a conexão com o banco de dados
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Erro ao conectar ao banco de dados.", e);
        }
    }

    // Método para fechar a conexão com o banco de dados
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException e) {
                throw new DatabaseConnectionException("Erro ao fechar a conexão com o banco de dados.", e);
            }
        }
    }
}
