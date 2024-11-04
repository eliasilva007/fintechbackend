package br.com.fiap.fintech.implementsDAO;

import br.com.fiap.fintech.exceptions.IdentificadorInvalidoException;
import br.com.fiap.fintech.interfaceDAO.ContaDAO;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.ContaFisica;
import br.com.fiap.fintech.model.ContaJuridica;
import br.com.fiap.fintech.tipoenum.TipoConta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImplementContaDAO implements ContaDAO {
    private static final Logger logger = Logger.getLogger(ImplementContaDAO.class.getName());

    private static final String SQL_INSERT = "INSERT INTO T_Conta (nome, email, numeroTelefone, senha, tipoConta, cpf, rg, dataNascimento, cnpj, razaoSocial, nomeFantasia, inscricaoEstadual) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE T_Conta SET nome = ?, email = ?, numeroTelefone = ?, senha = ?, tipoConta = ?, cpf = ?, rg = ?, dataNascimento = ?, cnpj = ?, razaoSocial = ?, nomeFantasia = ?, inscricaoEstadual = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM T_Conta WHERE id = ?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM T_Conta WHERE id = ?";
    private static final String SQL_LOGIN_CPF = "SELECT * FROM T_Conta WHERE cpf = ? AND senha = ?";
    private static final String SQL_LOGIN_CNPJ = "SELECT * FROM T_Conta WHERE cnpj = ? AND senha = ?";
    private static final String SQL_LIST_ALL = "SELECT * FROM T_Conta";

    private final Connection connection;

    public ImplementContaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void cadastrar(Conta conta) throws SQLException {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não pode ser nula");
        }

        try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, conta.getNome());
            stmt.setString(2, conta.getEmail());
            stmt.setString(3, conta.getNumeroTelefone());
            stmt.setString(4, conta.getSenha());
            stmt.setString(5, conta.getTipoConta().name());

            if (conta instanceof ContaFisica contaFisica) {
                stmt.setString(6, contaFisica.getCpf());
                stmt.setString(7, contaFisica.getRg());
                stmt.setDate(8, java.sql.Date.valueOf(contaFisica.getDataNascimento()));
                stmt.setNull(9, java.sql.Types.VARCHAR);
                stmt.setNull(10, java.sql.Types.VARCHAR);
                stmt.setNull(11, java.sql.Types.VARCHAR);
                stmt.setNull(12, java.sql.Types.VARCHAR);
            } else if (conta instanceof ContaJuridica contaJuridica) {
                stmt.setNull(6, java.sql.Types.VARCHAR);
                stmt.setNull(7, java.sql.Types.VARCHAR);
                stmt.setNull(8, java.sql.Types.DATE);
                stmt.setString(9, contaJuridica.getCnpj());
                stmt.setString(10, contaJuridica.getRazaoSocial());
                stmt.setString(11, contaJuridica.getNomeFantasia());
                stmt.setString(12, contaJuridica.getInscricaoEstadual());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao cadastrar a conta", e);
            throw e;
        }
    }

    @Override
    public void atualizar(Conta conta) throws SQLException {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não pode ser nula");
        }

        try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, conta.getNome());
            stmt.setString(2, conta.getEmail());
            stmt.setString(3, conta.getNumeroTelefone());
            stmt.setString(4, conta.getSenha());
            stmt.setString(5, conta.getTipoConta().name());

            if (conta instanceof ContaFisica contaFisica) {
                stmt.setString(6, contaFisica.getCpf());
                stmt.setString(7, contaFisica.getRg());
                stmt.setDate(8, java.sql.Date.valueOf(contaFisica.getDataNascimento()));
                stmt.setNull(9, java.sql.Types.VARCHAR);
                stmt.setNull(10, java.sql.Types.VARCHAR);
                stmt.setNull(11, java.sql.Types.VARCHAR);
                stmt.setNull(12, java.sql.Types.VARCHAR);
            } else if (conta instanceof ContaJuridica contaJuridica) {
                stmt.setNull(6, java.sql.Types.VARCHAR);
                stmt.setNull(7, java.sql.Types.VARCHAR);
                stmt.setNull(8, java.sql.Types.DATE);
                stmt.setString(9, contaJuridica.getCnpj());
                stmt.setString(10, contaJuridica.getRazaoSocial());
                stmt.setString(11, contaJuridica.getNomeFantasia());
                stmt.setString(12, contaJuridica.getInscricaoEstadual());
            }
            stmt.setInt(13, conta.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar a conta", e);
            throw e;
        }
    }

    @Override
    public void deletar(int idUsuario) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao deletar a conta com ID: " + idUsuario, e);
            throw e; // Lança a exceção após o log
        }
    }


    @Override
    public Conta buscarPorId(int idUsuario) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    TipoConta tipoConta = TipoConta.valueOf(rs.getString("tipoConta"));
                    if (tipoConta == TipoConta.FISICA) {
                        return new ContaFisica(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("numeroTelefone"),
                                rs.getString("senha"),
                                rs.getDate("dataNascimento").toLocalDate(),
                                rs.getString("cpf"),
                                rs.getString("rg")
                        );
                    } else if (tipoConta == TipoConta.JURIDICA) {
                        return new ContaJuridica(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("numeroTelefone"),
                                rs.getString("senha"),
                                rs.getString("cnpj"),
                                rs.getString("razaoSocial"),
                                rs.getString("nomeFantasia"),
                                rs.getString("inscricaoEstadual")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar conta por ID", e);
            throw e;
        }
        return null; // Retorna null se não encontrar a conta
    }

    @Override
    public Conta fazerLogin(String identificador, String senha) throws SQLException {
        if (identificador.length() != 11 && identificador.length() != 14) {
            throw new IdentificadorInvalidoException("Identificador inválido. CPF deve ter 11 dígitos e CNPJ 14 dígitos.");
        }

        String sql = identificador.length() == 11 ? SQL_LOGIN_CPF : SQL_LOGIN_CNPJ;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, identificador);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    TipoConta tipoConta = TipoConta.valueOf(rs.getString("tipoConta"));
                    if (tipoConta == TipoConta.FISICA) {
                        return new ContaFisica(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("numeroTelefone"),
                                rs.getString("senha"),
                                rs.getDate("dataNascimento").toLocalDate(),
                                rs.getString("cpf"),
                                rs.getString("rg")
                        );
                    } else if (tipoConta == TipoConta.JURIDICA) {
                        return new ContaJuridica(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("numeroTelefone"),
                                rs.getString("senha"),
                                rs.getString("cnpj"),
                                rs.getString("razaoSocial"),
                                rs.getString("nomeFantasia"),
                                rs.getString("inscricaoEstadual")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao realizar login", e);
            throw e;
        }
        return null; // Retorna null se o login falhar
    }

    @Override
    public List<Conta> listarTodas() throws SQLException {
        List<Conta> contas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL_LIST_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TipoConta tipoConta = TipoConta.valueOf(rs.getString("tipoConta"));
                if (tipoConta == TipoConta.FISICA) {
                    contas.add(new ContaFisica(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("numeroTelefone"),
                            rs.getString("senha"),
                            rs.getDate("dataNascimento").toLocalDate(),
                            rs.getString("cpf"),
                            rs.getString("rg")
                    ));
                } else if (tipoConta == TipoConta.JURIDICA) {
                    contas.add(new ContaJuridica(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("numeroTelefone"),
                            rs.getString("senha"),
                            rs.getString("cnpj"),
                            rs.getString("razaoSocial"),
                            rs.getString("nomeFantasia"),
                            rs.getString("inscricaoEstadual")
                    ));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar contas", e);
            throw e;
        }
        return contas;
    }
}
