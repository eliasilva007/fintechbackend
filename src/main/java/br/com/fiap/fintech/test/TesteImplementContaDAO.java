package br.com.fiap.fintech.test;

import br.com.fiap.fintech.factory.ConnectionFactory;

import java.sql.Connection;

public class TesteImplementContaDAO {
    private static Connection connection;
    private static ImplementContaDAO contaDAO;

    public static void main(String[] args) {
        setup();
        // Chame os métodos de teste a seguir...
    }

    private static void setup() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connection = connectionFactory.getConnection();

        // Criação da tabela para teste
        try {
            connection.createStatement().execute("CREATE TABLE T_Conta (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "nome VARCHAR(255), " +
                    "email VARCHAR(255), " +
                    "numeroTelefone VARCHAR(15), " +
                    "senha VARCHAR(255), " +
                    "tipoConta VARCHAR(10), " +
                    "cpf VARCHAR(11), " +
                    "rg VARCHAR(10), " +
                    "dataNascimento DATE, " +
                    "cnpj VARCHAR(14), " +
                    "razaoSocial VARCHAR(255), " +
                    "nomeFantasia VARCHAR(255), " +
                    "inscricaoEstadual VARCHAR(15))");

            contaDAO = new ImplementContaDAO(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
