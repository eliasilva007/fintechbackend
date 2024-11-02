package br.com.fiap.fintech.interfaceDAO;

import br.com.fiap.fintech.model.Divida;
import java.util.List;

public interface DividaDAO {
    void insert(Divida divida);
    List<Divida> getAll();
    void update(Divida divida);
    void delete(int idDivida);
    Divida getById(int idDivida);
}

