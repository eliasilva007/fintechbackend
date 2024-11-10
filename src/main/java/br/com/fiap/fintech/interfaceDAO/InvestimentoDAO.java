package br.com.fiap.fintech.interfaceDAO;

import br.com.fiap.fintech.model.Investimento;
import java.util.List;

public interface InvestimentoDAO {


        // Insere um novo investimento
        void insert(Investimento investimento);

        // Retorna todos os investimentos
        List<Investimento> getAll();

        // Atualiza um investimento existente
        void update(Investimento investimento);

        // Remove um investimento pelo ID
        void delete(int idInvestimento);

        // Retorna um investimento específico pelo ID
        Investimento getById(int idInvestimento);

    }



