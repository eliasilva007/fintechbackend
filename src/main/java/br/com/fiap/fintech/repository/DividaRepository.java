package br.com.fiap.fintech.repository;

import br.com.fiap.fintech.exceptions.AppException;
import br.com.fiap.fintech.interfaceDAO.DividaDAO;
import br.com.fiap.fintech.model.Divida;

import java.util.List;

public class DividaRepository {

    private final DividaDAO dividaDAO;

    public DividaRepository(DividaDAO dividaDAO) {
        this.dividaDAO = dividaDAO;
    }

    public void insert(Divida divida) {
        try {
            dividaDAO.insert(divida);
        } catch (Exception e) {
            throw new AppException("Erro ao inserir a dívida: " + e.getMessage(), e);
        }
    }

    public Divida getById(int id) {
        try {
            return dividaDAO.getById(id);
        } catch (Exception e) {
            throw new AppException("Erro ao buscar a dívida com ID " + id + ": " + e.getMessage(), e);
        }
    }

    public List<Divida> getAll() {
        try {
            return dividaDAO.getAll();
        } catch (Exception e) {
            throw new AppException("Erro ao buscar todas as dívidas: " + e.getMessage(), e);
        }
    }

    public void update(Divida divida) {
        try {
            dividaDAO.update(divida);
        } catch (Exception e) {
            throw new AppException("Erro ao atualizar a dívida: " + e.getMessage(), e);
        }
    }

    public void delete(int id) {
        try {
            dividaDAO.delete(id);
        } catch (Exception e) {
            throw new AppException("Erro ao deletar a dívida com ID " + id + ": " + e.getMessage(), e);
        }
    }
}
