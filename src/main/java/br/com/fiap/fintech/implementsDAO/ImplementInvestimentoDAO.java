package br.com.fiap.fintech.implementsDAO;

import br.com.fiap.fintech.exceptions.DatabaseConnectionException;
import br.com.fiap.fintech.factory.ConnectionFactory;
import br.com.fiap.fintech.interfaceDAO.InvestimentoDAO;
import br.com.fiap.fintech.model.Divida;
import br.com.fiap.fintech.model.Investimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ImplementInvestimentoDAO implements InvestimentoDAO {
    private final Connection conexao;

    public ImplementInvestimentoDAO() {
        try {
            this.conexao = ConnectionFactory.getConnection();
        } catch (DatabaseConnectionException e) {
            throw new DatabaseConnectionException("Erro ao conectar ao banco de dados.", e);
        }
    }


    @Override
    public void insert(Investimento investimento) {
        String sql = "INSERT INTO T_INVESTIMENTO (id_investimento, id_usuario, tipo, valor_investimento, taxa_retorno, data_inicio, data_fim )"
                + "VALUES(?, ?, ?, ?, ?, ?, ? ) ";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setLong(1, investimento.getIdInvestimento());
            stmt.setLong(2, investimento.getIdConta());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Investimento> getAll() {

    }

    @Override
    public void Update(Investimento investimento) {

    }

    @Override
    public void delete(int idInvestimento) {

    }

    @Override
    public Divida getById(int idDivida) {
        return null;
    }
}
