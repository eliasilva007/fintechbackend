// TestContaController.java
package br.com.fiap.fintech.test;

import br.com.fiap.fintech.controller.ContaController;
import br.com.fiap.fintech.factory.ConnectionFactory;
import br.com.fiap.fintech.implementsDAO.ImplementContaDAO;
import br.com.fiap.fintech.interfaceDAO.ContaDAO;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Divida;
import br.com.fiap.fintech.repository.ContaRepository;
import br.com.fiap.fintech.service.ContaService;
import br.com.fiap.fintech.tipoenum.TipoConta;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class TestContaController {
    public static void main(String[] args) {
         // Inicialize a conexão aqui
            // Instancie ContaDAO
            ContaDAO contaDAO = new ImplementContaDAO();

            // Instancie ContaRepository com ContaDAO
            ContaRepository contaRepository = new ContaRepository(contaDAO);

            // Instancie ContaService com ContaRepository
            ContaService contaService = new ContaService(contaRepository);

            // Instancie ContaController com ContaService
            ContaController contaController = new ContaController(contaService);

            // Teste o cadastro
            // Exemplo de uso


            Conta novaConta = new Conta(
                    0,
                    "Leonardo Moreira",
                    "Leonardo.silva@example.com",
                    "11912345678",
                    "senhaSegura123",
                    TipoConta.F
            );
            contaController.cadastrarConta(novaConta);

            for (Conta c : contaController.listarTodasContas()) {
                System.out.println(c.toString());
            }

            try {
                contaDAO.deletarConta(95);
                System.out.println("Conta removida com sucesso.");
            } catch (Exception e) {
                System.out.println("Erro ao remover conta " + e.getMessage());
            }

            contaController.listarTodasContas();

            contaController.buscarContaPorId(98);





    }
}

