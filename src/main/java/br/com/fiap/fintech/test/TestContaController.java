// TestContaController.java
package br.com.fiap.fintech.test;

import br.com.fiap.fintech.controller.ContaController;
import br.com.fiap.fintech.factory.ConnectionFactory;
import br.com.fiap.fintech.interfaceDAO.ContaDAO;
import br.com.fiap.fintech.model.ContaFisica;
import br.com.fiap.fintech.repository.ContaRepository;
import br.com.fiap.fintech.service.ContaService;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class TestContaController {
    public static void main(String[] args) {
        try (Connection connection = ConnectionFactory.getConnection()) { // Inicialize a conexão aqui
            // Instancie ContaDAO
            ContaDAO contaDAO = new ImplementContaDAO(connection);

            // Instancie ContaRepository com ContaDAO
            ContaRepository contaRepository = new ContaRepository(contaDAO);

            // Instancie ContaService com ContaRepository
            ContaService contaService = new ContaService(contaRepository);

            // Instancie ContaController com ContaService
            ContaController contaController = new ContaController(contaService);

            // Teste o cadastro
            // Exemplo de uso
            LocalDate novaDataNascimento = LocalDate.of(1990, 5, 20);

            ContaFisica novaConta = new ContaFisica(0, "João Silva", "joao.silva@example.com", "11912345678", "senhaSegura123",
                    novaDataNascimento, "12345678901", "12345678");
            contaController.cadastrarConta(connection, novaConta); // Passe a connection aqui também
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

