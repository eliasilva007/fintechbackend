package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.exceptions.CustomException;
import br.com.fiap.fintech.model.Divida;
import br.com.fiap.fintech.service.DividaService;

import java.util.List;

public class DividaController {
    private final DividaService dividaService;

    public DividaController(DividaService dividaService) {
        this.dividaService = dividaService;
    }

    // Criação de nova dívida
    public void criarDivida(Divida divida) {
        try {
            dividaService.criarDivida(divida);
            System.out.println("Dívida criada com sucesso: " + divida);
        } catch (CustomException e) {
            System.err.println("Erro ao criar dívida: " + e.getMessage());
        }
    }

    // Busca uma dívida pelo ID
    public Divida buscarDivida(int id) {
        try {
            Divida divida = dividaService.buscarDivida(id);
            if (divida != null) {
                System.out.println("Dívida encontrada: " + divida);
            } else {
                System.out.println("Dívida não encontrada com ID: " + id);
            }
            return divida;
        } catch (CustomException e) {
            System.err.println("Erro ao buscar dívida: " + e.getMessage());
            return null;
        }
    }

    // Lista todas as dívidas
    public List<Divida> listarDividas() {
        List<Divida> dividas = dividaService.listarDividas();
        System.out.println("Lista de dívidas: " + dividas);
        return dividas;
    }

    // Atualização de dívida existente
    public void atualizarDivida(Divida divida) {
        try {
            dividaService.atualizarDivida(divida);
            System.out.println("Dívida atualizada com sucesso: " + divida);
        } catch (CustomException e) {
            System.err.println("Erro ao atualizar dívida: " + e.getMessage());
        }
    }

    // Remoção de dívida
    public void removerDivida(int id) {
        try {
            dividaService.removerDivida(id);
            System.out.println("Dívida removida com sucesso, ID: " + id);
        } catch (CustomException e) {
            System.err.println("Erro ao remover dívida: " + e.getMessage());
        }
    }
}
