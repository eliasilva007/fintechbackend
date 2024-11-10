package br.com.fiap.fintech.test;

import br.com.fiap.fintech.implementsDAO.ImplementInvestimentoDAO;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Investimento;
import java.time.LocalDate;
import java.util.List;
import br.com.fiap.fintech.controller.ContaController;

public class TestInvestimentoDAO {

    public static void main(String[] args) {
        ImplementInvestimentoDAO investimentoDAO = new ImplementInvestimentoDAO();

        // 1. Teste de Inserção (Create)
        Investimento novoInvestimento = new Investimento();
        novoInvestimento.setTipo("Ações");
        novoInvestimento.setValorInvestimento(10000.0);
        novoInvestimento.setTaxaRetornoInvestimento(8.5);
        novoInvestimento.setDataInicio(LocalDate.of(2023, 1, 1));
        novoInvestimento.setDataFim(LocalDate.of(2024, 1, 1));


        investimentoDAO.insert(novoInvestimento);
        System.out.println("Investimento inserido com ID: " + novoInvestimento.getIdInvestimento());

        // 2. Teste de Recuperação por ID (Read)
        Investimento investimentoRecuperado = investimentoDAO.getById(novoInvestimento.getIdInvestimento());
        System.out.println("Investimento recuperado: " + investimentoRecuperado);

        // 3. Teste de Atualização (Update)
        investimentoRecuperado.setValorInvestimento(15000.0);
        investimentoDAO.update(investimentoRecuperado);
        System.out.println("Investimento atualizado com novo valor: " + investimentoRecuperado.getValorInvestimento());

        // 4. Teste de Listagem de Todos os Investimentos (Read All)
        List<Investimento> investimentos = investimentoDAO.getAll();
        System.out.println("Lista de todos os investimentos:");
        for (Investimento investimento : investimentos) {
            System.out.println(investimento);
        }

        // 5. Teste de Exclusão (Delete)
        investimentoDAO.delete(novoInvestimento.getIdInvestimento());
        System.out.println("Investimento deletado com ID: " + novoInvestimento.getIdInvestimento());

        // Verificação final para confirmar exclusão
        Investimento investimentoDeletado = investimentoDAO.getById(novoInvestimento.getIdInvestimento());
        if (investimentoDeletado == null) {
            System.out.println("Confirmação: Investimento foi removido do banco de dados.");
        } else {
            System.out.println("Erro: Investimento ainda está presente no banco de dados.");
        }
    }
}
