package br.com.fiap.fintech.test;

import br.com.fiap.fintech.controller.DividaController;
import br.com.fiap.fintech.implementsDAO.ImplementDividaDAO;
import br.com.fiap.fintech.interfaceDAO.DividaDAO;
import br.com.fiap.fintech.model.Divida;
import br.com.fiap.fintech.repository.DividaRepository;
import br.com.fiap.fintech.service.DividaService;

import java.time.LocalDate;

public class TesteDividaController {
    public static void main(String[] args) {
        // Criando a implementação da DAO
        DividaDAO dividaDAO = new ImplementDividaDAO();
        // Criando a instância do repositório
        DividaRepository dividaRepository = new DividaRepository(dividaDAO); // Certifique-se de que você tenha um repositório funcional
        // Criando a instância do serviço
        DividaService dividaService = new DividaService(dividaRepository);
        // Criando a instância do controlador
        DividaController dividaController = new DividaController(dividaService);

        // Criando uma nova dívida para teste
        Divida divida = new Divida(
                500.00,                           // valorTotal
                500.00,                            // valorPago
                LocalDate.now().plusDays(30),     // dataVencimento
                LocalDate.now(),                   // dataCriacao
                "Em Aberto",                       // statusDivida
                "Agiota",                         // tipoDivida
                "Dinheiro emprestado",                // descricaoDivida
                1                                   // idDivida
        );

        // Testando a criação da dívida
        try {
            dividaController.criarDivida(divida);
            System.out.println("Dívida criada com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar dívida: " + e.getMessage());
        }

        // Testando a busca da dívida
        Divida dividaBuscada = dividaController.buscarDivida(49);
        if (dividaBuscada != null) {
            System.out.println("Dívida encontrada: " + dividaBuscada.getDescricaoDivida());
        } else {
            System.out.println("Dívida não encontrada.");
        }

        // Testando a listagem de dívidas
        System.out.println("Listando dívidas:");
        for (Divida d : dividaController.listarDividas()) {
            System.out.println("- " + d.getDescricaoDivida());
        }

        // Testando a atualização da dívida
        try {
            dividaController.atualizarDivida(divida);
            System.out.println("Dívida atualizada com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar dívida: " + e.getMessage());
        }

        // Testando a remoção da dívida
        try {
            dividaController.removerDivida(49);
            System.out.println("Dívida removida com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao remover dívida: " + e.getMessage());
        }
    }
}
