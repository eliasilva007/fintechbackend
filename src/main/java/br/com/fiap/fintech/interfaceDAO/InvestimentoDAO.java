package br.com.fiap.fintech.interfaceDAO;

import br.com.fiap.fintech.model.Divida;
import br.com.fiap.fintech.model.Investimento;

import java.util.List;

public interface InvestimentoDAO {
    void insert (Investimento investimento);
    List<Investimento> getAll ();
    void Update (Investimento investimento);
    void delete (int idInvestimento);
    Divida getById (int idDivida);

}
