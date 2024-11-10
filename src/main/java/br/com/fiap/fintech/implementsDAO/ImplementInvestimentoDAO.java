package br.com.fiap.fintech.implementsDAO;

import br.com.fiap.fintech.interfaceDAO.InvestimentoDAO;
import br.com.fiap.fintech.model.Investimento;
import br.com.fiap.fintech.factory.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ImplementInvestimentoDAO implements InvestimentoDAO {

    private Connection connection;

    public ImplementInvestimentoDAO() {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Investimento investimento) {
        String sql = "INSERT INTO T_investimento (tipo, valor_investimento, taxa_retorno, dt_inicio, dt_fim) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, investimento.getTipo());
            stmt.setDouble(2, investimento.getValorInvestimento());
            stmt.setDouble(3, investimento.getTaxaRetornoInvestimento());
            stmt.setDate(4, Date.valueOf(investimento.getDataInicio()));
            stmt.setDate(5, Date.valueOf(investimento.getDataFim()));


            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                investimento.setIdInvestimento(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Investimento> getAll() {
        List<Investimento> investimentos = new ArrayList<>();
        String sql = "SELECT * FROM T_investimento";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Investimento investimento = new Investimento();
                investimento.setIdInvestimento(rs.getInt("id_investimento"));
                investimento.setTipo(rs.getString("tipo"));
                investimento.setValorInvestimento(rs.getDouble("valor_investimento"));
                investimento.setTaxaRetornoInvestimento(rs.getDouble("taxa_retorno"));
                investimento.setDataInicio(rs.getDate("dt_inicio").toLocalDate());
                investimento.setDataFim(rs.getDate("dt_fim").toLocalDate());

                // Aqui você pode buscar e associar a conta com o investimento, se necessário
                // investimento.setConta(algumaLogicaParaObterConta(rs.getInt("id_usuario")));

                investimentos.add(investimento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return investimentos;
    }

    @Override
    public void update(Investimento investimento) {
        String sql = "UPDATE T_investimento SET tipo = ?, valor_investimento = ?, taxa_retorno = ?, dt_inicio = ?, dt_fim = ? WHERE id_investimento = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, investimento.getTipo());
            stmt.setDouble(2, investimento.getValorInvestimento());
            stmt.setDouble(3, investimento.getTaxaRetornoInvestimento());
            stmt.setDate(4, Date.valueOf(investimento.getDataInicio()));
            stmt.setDate(5, Date.valueOf(investimento.getDataFim()));
            stmt.setInt(6, investimento.getIdInvestimento());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int idInvestimento) {
        String sql = "DELETE FROM T_investimento WHERE id_investimento = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idInvestimento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Investimento getById(int idInvestimento) {
        String sql = "SELECT * FROM T_investimento WHERE id_investimento = ?";
        Investimento investimento = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idInvestimento);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                investimento = new Investimento();
                investimento.setIdInvestimento(rs.getInt("id_investimento"));
                investimento.setTipo(rs.getString("tipo"));
                investimento.setValorInvestimento(rs.getDouble("valor_investimento"));
                investimento.setTaxaRetornoInvestimento(rs.getDouble("taxa_retorno"));
                investimento.setDataInicio(rs.getDate("dt_inicio").toLocalDate());
                investimento.setDataFim(rs.getDate("dt_fim").toLocalDate());

                // Aqui você pode buscar e associar a conta com o investimento, se necessário
                // investimento.setConta(algumaLogicaParaObterConta(rs.getInt("id_usuario")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return investimento;
    }
}
