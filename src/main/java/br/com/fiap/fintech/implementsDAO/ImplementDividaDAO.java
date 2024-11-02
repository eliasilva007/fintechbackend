package br.com.fiap.fintech.implementsDAO;


import br.com.fiap.fintech.exceptions.DatabaseConnectionException;
import br.com.fiap.fintech.factory.ConnectionFactory;
import br.com.fiap.fintech.interfaceDAO.DividaDAO;
import br.com.fiap.fintech.model.Divida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplementDividaDAO implements DividaDAO {

    private final Connection conexao;

    public ImplementDividaDAO() {
        try {
            this.conexao = ConnectionFactory.getConnection();
        } catch (DatabaseConnectionException e) {
            throw new DatabaseConnectionException("Erro ao conectar ao banco de dados.", e);
        }
    }

    @Override
    public void insert(Divida divida) {
        String sql = "INSERT INTO T_Divida (id_divida, valor_total, valor_pago, data_vencimento, status, tipo, descricao) " +
                "VALUES (RM558943.SEQ_DIVIDA.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, divida.getValorTotal());
            stmt.setDouble(2, divida.getValorPago());
            stmt.setDate(3, java.sql.Date.valueOf(divida.getDataVencimento()));
            stmt.setString(4, divida.getStatusDivida());
            stmt.setString(5, divida.getTipoDivida());
            stmt.setString(6, divida.getDescricaoDivida());
            stmt.setDate(7, java.sql.Date.valueOf(divida.getDataCriacao()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir a dívida", e);
        }
    }

    @Override
    public List<Divida> getAll() {
        List<Divida> dividas = new ArrayList<>();
        String sql = "SELECT * FROM T_Divida";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Divida divida = new Divida();
                divida.setIdDivida(rs.getInt("id_divida"));
                divida.setValorTotal(rs.getDouble("valor_total"));
                divida.setValorPago(rs.getDouble("valor_pago"));
                divida.setDataVencimento(rs.getDate("data_vencimento").toLocalDate());
                divida.setStatusDivida(rs.getString("status"));
                divida.setTipoDivida(rs.getString("tipo"));
                divida.setDescricaoDivida(rs.getString("descricao"));
                divida.setDataVencimento(rs.getDate("data_criacao").toLocalDate());
                dividas.add(divida);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar as dívidas", e);
        }

        return dividas;
    }

    @Override
    public void update(Divida divida) {
        String sql = "UPDATE T_Divida SET valor_total = ?, valor_pago = ?, data_vencimento = ?, status = ?, tipo = ?, descricao = ?, data_criacao = ? WHERE id_divida = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, divida.getValorTotal());
            stmt.setDouble(2, divida.getValorPago());
            stmt.setDate(3, java.sql.Date.valueOf(divida.getDataVencimento()));
            stmt.setString(4, divida.getStatusDivida());
            stmt.setString(5, divida.getTipoDivida());
            stmt.setString(6, divida.getDescricaoDivida());
            stmt.setDate(7, java.sql.Date.valueOf(divida.getDataCriacao()));
            stmt.setInt(8, divida.getIdDivida());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a dívida", e);
        }
    }

    @Override
    public void delete(int idDivida) {
        String sql = "DELETE FROM T_Divida WHERE id_divida = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idDivida);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover a dívida", e);
        }
    }

    @Override
    public Divida getById(int idDivida) {
        String sql = "SELECT * FROM T_Divida WHERE id_divida = ?";
        Divida divida = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idDivida);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    divida = new Divida();
                    divida.setIdDivida(rs.getInt("id_divida"));
                    divida.setValorTotal(rs.getDouble("valor_total"));
                    divida.setValorPago(rs.getDouble("valor_pago"));
                    divida.setDataVencimento(rs.getDate("data_vencimento").toLocalDate());
                    divida.setStatusDivida(rs.getString("status"));
                    divida.setTipoDivida(rs.getString("tipo"));
                    divida.setDescricaoDivida(rs.getString("descricao"));
                    divida.setDataCriacao(rs.getDate("data_criacao").toLocalDate());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar a dívida pelo ID", e);
        }

        return divida;
    }
}

