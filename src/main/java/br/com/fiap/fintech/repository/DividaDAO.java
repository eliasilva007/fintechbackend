package br.com.fiap.fintech.repository;

import br.com.fiap.fintech.factory.ConnectionFactory;
import br.com.fiap.fintech.model.Divida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DividaDAO {
    private Connection conexao;

    public DividaDAO() {
        try {
            this.conexao = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

    public void insert(Divida divida) {
        // Insere a dívida, usando a sequência para o ID_DIVIDA
        String sql = "INSERT INTO T_Divida (id_divida, valor_total, valor_pago, data_vencimento, status, tipo, descricao) " +
                "VALUES (RM558943.SEQ_DIVIDA.NEXTVAL, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            // Preenche os outros campos normalmente
            stmt.setDouble(1, divida.getValorTotal());
            stmt.setDouble(2, divida.getValorPago());
            stmt.setDate(3, java.sql.Date.valueOf(divida.getDataVencimento()));
            stmt.setString(4, divida.getStatusDivida());
            stmt.setString(5, divida.getTipoDivida());
            stmt.setString(6, divida.getDescricaoDivida());

            // Executa a inserção
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir a dívida", e);
        }
    }


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
                // Adicione a lógica para vincular a dívida ao usuário, se necessário
                dividas.add(divida);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar as dívidas", e);
        }
        return dividas;
    }
    public void update(Divida divida) {
        String sql = "UPDATE T_Divida SET valor_total = ?, valor_pago = ?, data_vencimento = ?, status = ?, tipo = ?, descricao = ? WHERE id_divida = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, divida.getValorTotal());
            stmt.setDouble(2, divida.getValorPago());
            stmt.setDate(3, java.sql.Date.valueOf(divida.getDataVencimento()));
            stmt.setString(4, divida.getStatusDivida());
            stmt.setString(5, divida.getTipoDivida());
            stmt.setString(6, divida.getDescricaoDivida());
            stmt.setInt(7, divida.getIdDivida()); // Usar o ID da dívida para atualizá-la
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a dívida", e);
        }
    }
    public void delete(int idDivida) {
        String sql = "DELETE FROM T_Divida WHERE id_divida = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idDivida);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover a dívida", e);
        }
    }
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
                    divida.setStatusDivida(rs.getString("status_divida"));
                    divida.setTipoDivida(rs.getString("tipo_divida"));
                    divida.setDescricaoDivida(rs.getString("descricao_divida"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar a dívida pelo ID", e);
        }

        return divida;
    }

    // Lembre-se de implementar um método para fechar a conexão, se necessário.
}
