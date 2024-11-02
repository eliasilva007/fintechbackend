package br.com.fiap.fintech.repository;

import br.com.fiap.fintech.interfaceDAO.DividaDAO;
import br.com.fiap.fintech.model.Divida;

import java.util.List;

public class DividaRepository {

    private final DividaDAO dividaDAO;

    public DividaRepository(DividaDAO dividaDAO) {
        this.dividaDAO = dividaDAO;
    }


    public void insert(Divida divida) {
        dividaDAO.insert(divida);
    }

    public Divida getById(int id) {
        return dividaDAO.getById(id);
    }

    public List<Divida> getAll() {
        return dividaDAO.getAll();
    }

    public void update(Divida divida) {
        dividaDAO.update(divida);
    }

    public void delete(int id) {
        dividaDAO.delete(id);
    }
}
