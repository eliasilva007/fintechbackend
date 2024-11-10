package br.com.fiap.fintech.implementsDAO;

import br.com.fiap.fintech.exceptions.DatabaseConnectionException;
import br.com.fiap.fintech.factory.ConnectionFactory;
import br.com.fiap.fintech.interfaceDAO.ContaDAO;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.tipoenum.TipoConta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class ImplementContaDAO implements ContaDAO {
    private static final Logger logger = Logger.getLogger(ImplementContaDAO.class.getName());
    private final Connection conexao;

    public ImplementContaDAO() {
        try {
            this.conexao = ConnectionFactory.getConnection();
        } catch (DatabaseConnectionException e) {
            throw new DatabaseConnectionException("Erro ao conectar ao banco de dados.", e);
        }
    }

    @Override
    public void cadastrar(Conta conta) throws SQLException {
        String sqlUsuario = "INSERT INTO T_USUARIO (nm_usuario, email, nr_telefone, senha, tipo_pessoa) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sqlUsuario) ){
            stmt.setString(1, conta.getNome());
            stmt.setString(2, conta.getEmail());
            stmt.setString(3, conta.getNumeroTelefone());
            stmt.setString(4, conta.getSenha());
            stmt.setString(5, conta.getTipoConta().name());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar a Conta", e);
        }
    }

    @Override
    public void atualizar(Conta conta) throws SQLException {
        String sqlUsuario = "UPDATE INTO T_USUARIO SET nm_usuario = ?, email  = ?, nr_telefone = ?, senha = ?, tipo_pessoa = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sqlUsuario)) {
            stmt.setString(1,conta.getNome());
            stmt.setString(2, conta.getEmail());
            stmt.setString(3, conta.getSenha());
            stmt.setString(4, conta.getNumeroTelefone());
            stmt.setString(5, conta.getTipoConta().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a dívida", e);
        }
    }

    @Override
    public void deletarConta (int idUsuario) throws SQLException {
        String sql = "DELETE FROM T_USUARIO WHERE id_usuario = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover a conta", e);
        }
    }

    @Override
    public Conta buscarPorId(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM T_DIVIDA WHERE id_usuario= ? ";
        Conta conta = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    conta = new Conta();
                    conta.setId(rs.getInt("id_usuario"));
                    conta.setNome(rs.getString("nm_usuario"));
                    conta.setEmail(rs.getString("email"));
                    conta.setSenha(rs.getString("senha"));
                    conta.setTipoConta(TipoConta.valueOf(rs.getString("tipo_pessoa")));
                    conta.setNumeroTelefone(rs.getString("nr_telefone"));

                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar a dívida pelo ID", e);
        }
        return conta;
    }

    @Override
    public Conta fazerLogin(String identificador, String senha) throws SQLException {
        return null;
    }

    @Override
    public List<Conta> listarTodasContas() throws SQLException {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT * FROM T_USUARIO";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Conta conta = new Conta();
                conta.setId(rs.getInt("id_usuario"));
                conta.setNome(rs.getString("nm_usuario"));
                conta.setEmail(rs.getString("email"));
                conta.setSenha(rs.getString("senha"));
                conta.setNumeroTelefone(rs.getString("nr_telefone"));
                conta.setTipoConta(TipoConta.valueOf(rs.getString("tipo_pessoa")));
                contas.add(conta);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as dívidas", e);
        }

        return contas;
    }

}