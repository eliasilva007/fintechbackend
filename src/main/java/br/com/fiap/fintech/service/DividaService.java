package br.com.fiap.fintech.service;

import br.com.fiap.fintech.exceptions.CustomException;
import br.com.fiap.fintech.model.Divida;
import br.com.fiap.fintech.repository.DividaRepository;

import java.time.LocalDate;
import java.util.List;

public class DividaService {
    private final DividaRepository dividaRepository;

    public DividaService(DividaRepository dividaRepository) {
        this.dividaRepository = dividaRepository;
    }

    // Criação de nova dívida
    public void criarDivida(Divida divida) {
        validarDivida(divida);
        dividaRepository.insert(divida);
    }

    // Busca uma dívida pelo ID
    public Divida buscarDivida(int id) {
        return dividaRepository.getById(id);
    }

    // Lista todas as dívidas
    public List<Divida> listarDividas() {
        return dividaRepository.getAll();
    }

    // Atualização de dívida existente
    public void atualizarDivida(Divida divida) {
        validarDivida(divida);
        dividaRepository.update(divida);
    }

    // Remoção de dívida
    public void removerDivida(int id) {
        dividaRepository.delete(id);
    }

    // Método de validação centralizado para aplicar as regras de negócio
    private void validarDivida(Divida divida) {
        // 1. Valor da dívida e valor pago devem ser positivos
        if (divida.getValorTotal() <= 0) {
            throw new CustomException("O valor total da dívida deve ser positivo.");
        }
        if (divida.getValorPago() < 0) {
            throw new CustomException("O valor pago não pode ser negativo.");
        }

        // 2. Valor pago não pode exceder o valor total
        if (divida.getValorPago() > divida.getValorTotal()) {
            throw new CustomException("O valor pago não pode ser maior que o valor total da dívida.");
        }

        // 3. Atualização do status da dívida com base no valor pago
        if (divida.getValorPago() == divida.getValorTotal()) {
            divida.setStatusDivida("Paga");
        } else {
            divida.setStatusDivida("Em Aberto");
        }

        // 4. Data de criação e vencimento da dívida
        if (divida.getDataVencimento().isBefore(divida.getDataCriacao())) {
            throw new CustomException("A data de vencimento não pode ser anterior à data de criação.");
        }
        if (divida.getDataCriacao().isAfter(LocalDate.now())) {
            throw new CustomException("A data de criação da dívida não pode estar no futuro.");
        }

        // 5. Descrição da dívida
        if (divida.getDescricaoDivida() == null || divida.getDescricaoDivida().trim().isEmpty()) {
            throw new CustomException("A descrição da dívida é obrigatória.");
        }

    }
}

