package br.com.fiap.fintech.implementsDAO;

import br.com.fiap.fintech.exceptions.DatabaseConnectionException;
import br.com.fiap.fintech.exceptions.IdentificadorInvalidoException;
import br.com.fiap.fintech.factory.ConnectionFactory;
import br.com.fiap.fintech.interfaceDAO.ContaDAO;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.ContaFisica;
import br.com.fiap.fintech.model.ContaJuridica;
import br.com.fiap.fintech.tipoenum.TipoConta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImplementContaDAO implements ContaDAO {
    private static final Logger logger = Logger.getLogger(ImplementContaDAO.class.getName());

    private static final String SQL_INSERT_USUARIO = "INSERT INTO T_USUARIO (nm_usuario, email, nm_telefone, senha, tipo_pessoa) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_FISICA = "INSERT INTO T_PESSOA_FISICA (id_usuario, nr_cpf, nr_rg, dt_nascimento) VALUES (?, ?, ?, ?)";
    private static final String SQL_INSERT_JURIDICA = "INSERT INTO T_PESSOA_JURIDICA (id_usuario, cnpj, razaoSocial, nomeFantasia, inscricaoEstadual) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM T_USUARIO WHERE id = ?";

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
        if (conta == null) {
            throw new IllegalArgumentException("Conta não pode ser nula");
        }

        try (PreparedStatement stmtUsuario = conexao.prepareStatement(SQL_INSERT_USUARIO, Statement.RETURN_GENERATED_KEYS)) {
            // Inserção na tabela T_USUARIO
            preencherDadosConta(stmtUsuario, conta);
            stmtUsuario.executeUpdate();

            try (ResultSet generatedKeys = stmtUsuario.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idUsuario = generatedKeys.getInt(1);

                    if (conta instanceof ContaFisica) {
                        try (PreparedStatement stmtFisica = conexao.prepareStatement(SQL_INSERT_FISICA)) {
                            ContaFisica contaFisica = (ContaFisica) conta;
                            stmtFisica.setInt(1, idUsuario);
                            stmtFisica.setString(2, contaFisica.getCpf());
                            stmtFisica.setString(3, contaFisica.getRg());
                            stmtFisica.setDate(4, java.sql.Date.valueOf(contaFisica.getDataNascimento()));
                            stmtFisica.executeUpdate();
                            logger.info("Conta Física inserida com sucesso.");
                        }
                    } else if (conta instanceof ContaJuridica) {
                        try (PreparedStatement stmtJuridica = conexao.prepareStatement(SQL_INSERT_JURIDICA)) {
                            ContaJuridica contaJuridica = (ContaJuridica) conta;
                            stmtJuridica.setInt(1, idUsuario);
                            stmtJuridica.setString(2, contaJuridica.getCnpj());
                            stmtJuridica.setString(3, contaJuridica.getRazaoSocial());
                            stmtJuridica.setString(4, contaJuridica.getNomeFantasia());
                            stmtJuridica.setString(5, contaJuridica.getInscricaoEstadual());
                            stmtJuridica.executeUpdate();
                            logger.info("Conta Jurídica inserida com sucesso.");
                        }
                    }
                } else {
                    throw new SQLException("Falha ao obter ID gerado para a conta.");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao inserir a Conta", e);
            throw e;
        }
    }

    private void preencherDadosConta(PreparedStatement stmt, Conta conta) throws SQLException {
        stmt.setString(1, conta.getNome());
        stmt.setString(2, conta.getEmail());
        stmt.setString(3, conta.getNumeroTelefone());
        stmt.setString(4, conta.getSenha());
        stmt.setString(5, conta.getTipoConta().name());
    }

    private Conta construirConta(ResultSet rs) throws SQLException {
        TipoConta tipoConta = TipoConta.valueOf(rs.getString("tipo_pessoa"));
        if (tipoConta == TipoConta.FISICA) {
            return new ContaFisica(
                    rs.getInt("id"),
                    rs.getString("nm_usuario"),
                    rs.getString("email"),
                    rs.getString("nm_telefone"),
                    rs.getString("senha"),
                    rs.getDate("dt_nascimento").toLocalDate(),
                    rs.getString("nr_cpf"),
                    rs.getString("nr_rg")
            );
        } else if (tipoConta == TipoConta.JURIDICA) {
            return new ContaJuridica(
                    rs.getInt("id"),
                    rs.getString("nm_usuario"),
                    rs.getString("email"),
                    rs.getString("nm_telefone"),
                    rs.getString("senha"),
                    rs.getString("cnpj"),
                    rs.getString("razaoSocial"),
                    rs.getString("nomeFantasia"),
                    rs.getString("inscricaoEstadual")
            );
        }
        return null;
    }

    // Outros métodos (atualizar, deletar, buscarPorId, etc.) implementados conforme necessário...
}
