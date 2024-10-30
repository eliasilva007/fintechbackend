package br.com.fiap.fintech.test;

import br.com.fiap.fintech.dao.DividaDAO;
import br.com.fiap.fintech.factory.ConnectionFactory;
import br.com.fiap.fintech.functions.Divida;

import java.time.LocalDate;
import java.util.List;

public class DividaDaoTest {
    public static void main(String[] args) {

        DividaDAO dividaDAO = new DividaDAO();

        // CREATE - Inserir uma nova dívida
        Divida novaDivida = new Divida();
        novaDivida.setValorTotal(3000.00);
        novaDivida.setValorPago(500.00);
        novaDivida.setDataVencimento(LocalDate.now().plusDays(10));
        novaDivida.setStatusDivida("Ativa");
        novaDivida.setTipoDivida("Cartão de Crédito");
        novaDivida.setDescricaoDivida("Compra parcelada no cartão");

        dividaDAO.insert(novaDivida);
        System.out.println("Nova dívida inserida com sucesso!");

        // READ - Listar todas as dívidas
        List<Divida> dividas = dividaDAO.getAll();
        System.out.println("Lista de dívidas:");
        for (Divida divida : dividas) {
            System.out.println("ID: " + divida.getIdDivida());
            System.out.println("Valor Total: " + divida.getValorTotal());
            System.out.println("Valor Pago: " + divida.getValorPago());
            System.out.println("Data Vencimento: " + divida.getDataVencimento());
            System.out.println("Status: " + divida.getStatusDivida());
            System.out.println("Tipo: " + divida.getTipoDivida());
            System.out.println("Descrição: " + divida.getDescricaoDivida());
            System.out.println("------------------------------");
        }

        // UPDATE - Atualizar uma dívida existente
        if (!dividas.isEmpty()) {
            Divida primeiraDivida = dividas.get(0); // Pegando a primeira dívida da lista para atualizar
            primeiraDivida.setValorPago(1500.00); // Alterando o valor pago
            primeiraDivida.setStatusDivida("Quitada"); // Alterando o status para quitada

            dividaDAO.update(primeiraDivida); // Supondo que o método `update` existe no DAO
            System.out.println("Dívida atualizada com sucesso!");


            // DELETE - Remover uma dívida
            if (!dividas.isEmpty()) {
                Divida ultimaDivida = dividas.get(dividas.size() - 1); // Pegando a última dívida da lista
                dividaDAO.delete(ultimaDivida.getIdDivida()); // Supondo que o método `delete` existe no DAO
                System.out.println("Dívida removida com sucesso!");

                // Verifica a remoção
                List<Divida> dividasPosRemocao = dividaDAO.getAll();
                boolean removida = dividasPosRemocao.stream()
                        .noneMatch(d -> d.getIdDivida() == ultimaDivida.getIdDivida());
                if (removida) {
                    System.out.println("A dívida foi removida corretamente.");
                } else {
                    System.out.println("Erro ao remover a dívida.");
                }
            }
        }
    }
}