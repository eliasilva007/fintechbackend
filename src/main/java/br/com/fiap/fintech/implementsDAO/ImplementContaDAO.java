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

    // Atualize as queries para refletir as tabelas separadas para fisica e juridica
    private static final String SQL_INSERT_CONTA= "INSERT INTO T_USUARIO (nm_usuario, email, nm_telefone, senha, tipo_pessoa) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_FISICA = "INSERT INTO T_PESSOA_FISICA (nr_cpf, nr_rg, dt_nascimento) VALUES (?, ?, ?)";
    private static final String SQL_INSERT_JURIDICA = "INSERT INTO T_PESSOA_JURIDICA (cnpj, razaoSocial, nomeFantasia, inscricaoEstadual) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE T_USUARIO SET nome = ?, email = ?, numeroTelefone = ?, senha = ?, tipoConta = ?, cpf = ?, rg = ?, dataNascimento = ?, cnpj = ?, razaoSocial = ?, nomeFantasia = ?, inscricaoEstadual = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM T_USUARIO WHERE id = ?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM T_USUARIO WHERE id = ?";
    private static final String SQL_LOGIN_CPF = "SELECT * FROM T_USUARIO WHERE cpf = ? AND senha = ?";
    private static final String SQL_LOGIN_CNPJ = "SELECT * FROM T_USUARIO WHERE cnpj = ? AND senha = ?";
    private static final String SQL_LIST_ALL = "SELECT * FROM T_USUARIO";
    private final Connection conexao;

    public ImplementContaDAO(Connection connection) {
        try {
            this.conexao = ConnectionFactory.getConnection();
        } catch (DatabaseConnectionException e) {
            throw new DatabaseConnectionException("Erro ao conectar ao banco de dados.", e);
        }
    }

    @Override
    public void cadastrar(Conta conta) throws SQLException {
        String sql = "INSERT INTO T_USUARIO (nm_usuario, email, nr_telefone, senha, tipo_pessoa) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, conta.getNome());
            stmt.setString(2, conta.getEmail());
            stmt.setString(3, conta.getNumeroTelefone());
            stmt.setString(4, conta.getSenha());           // senha deve estar na posição 4
            stmt.setString(5, conta.getTipoConta().name()); // tipo_pessoa deve estar na posição 5
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir a Conta", e);
        }
    }


    //    @Override
//    public void cadastrar(Conta conta) throws SQLException {
//        if (conta == null) {
//            throw new IllegalArgumentException("Conta não pode ser nula");
//        }
//
//        // Verificar o tipo de conta e usar a SQL de inserção correta
//        try (PreparedStatement stmt = connection.prepareStatement(
//
//                //Preciso instanciar a conta aqui para depois eu poder prosseguir para a conta fisica ou juridica
//
//
//                conta instanceof ContaFisica ? SQL_INSERT_FISICA : SQL_INSERT_JURIDICA
//        )) {
//            preencherDadosConta(stmt, conta);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            logger.log(Level.SEVERE, "Erro ao cadastrar a conta", e);
//            throw e;
//        }
//    }
//
//    @Override
//    public void atualizar(Conta conta) throws SQLException {
//        if (conta == null) {
//            throw new IllegalArgumentException("Conta não pode ser nula");
//        }
//
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
//            preencherDadosConta(stmt, conta);
//            stmt.setInt(13, conta.getId());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            logger.log(Level.SEVERE, "Erro ao atualizar a conta", e);
//            throw e;
//        }
//    }
//
//    @Override
//    public void deletar(int idUsuario) throws SQLException {
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
//            stmt.setInt(1, idUsuario);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            logger.log(Level.SEVERE, "Erro ao deletar a conta com ID: " + idUsuario, e);
//            throw e;
//        }
//    }
//
//    @Override
//    public Conta buscarPorId(int idUsuario) throws SQLException {
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID)) {
//            stmt.setInt(1, idUsuario);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return construirConta(rs);
//                }
//            }
//        } catch (SQLException e) {
//            logger.log(Level.SEVERE, "Erro ao buscar conta por ID", e);
//            throw e;
//        }
//        return null;
//    }
//
//    @Override
//    public Conta fazerLogin(String identificador, String senha) throws SQLException {
//        if (identificador.length() != 11 && identificador.length() != 14) {
//            throw new IdentificadorInvalidoException("Identificador inválido. CPF deve ter 11 dígitos e CNPJ 14 dígitos.");
//        }
//
//        String sql = identificador.length() == 11 ? SQL_LOGIN_CPF : SQL_LOGIN_CNPJ;
//
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, identificador);
//            stmt.setString(2, senha);
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return construirConta(rs);
//                }
//            }
//        } catch (SQLException e) {
//            logger.log(Level.SEVERE, "Erro ao realizar login", e);
//            throw e;
//        }
//        return null;
//    }
//
//    @Override
//    public List<Conta> listarTodas() throws SQLException {
//        List<Conta> contas = new ArrayList<>();
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_LIST_ALL);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                contas.add(construirConta(rs));
//            }
//        } catch (SQLException e) {
//            logger.log(Level.SEVERE, "Erro ao listar contas", e);
//            throw e;
//        }
//        return contas;
//    }
//
//
//
    private void preencherDadosConta(PreparedStatement stmt, Conta conta) throws SQLException {
        stmt.setString(1, conta.getNome());
        stmt.setString(2, conta.getEmail());
        stmt.setString(3, conta.getNumeroTelefone());
        stmt.setString(4, conta.getSenha());
        stmt.setString(5, conta.getTipoConta().name()); }

//        if (conta instanceof ContaFisica contaFisica) {
//            stmt.setString(6, contaFisica.getCpf());  // Coluna CPF
//            stmt.setString(7, contaFisica.getRg());   // Coluna RG
//            stmt.setDate(8, Date.valueOf(contaFisica.getDataNascimento())); // Coluna dataNascimento
//            stmt.setNull(9, Types.VARCHAR);  // CNPJ (null para ContaFisica)
//            stmt.setNull(10, Types.VARCHAR); // RazaoSocial (null para ContaFisica)
//            stmt.setNull(11, Types.VARCHAR); // NomeFantasia (null para ContaFisica)
//            stmt.setNull(12, Types.VARCHAR); // InscricaoEstadual (null para ContaFisica)
//        } else if (conta instanceof ContaJuridica contaJuridica) {
//            stmt.setNull(6, Types.VARCHAR);  // CPF (null para ContaJuridica)
//            stmt.setNull(7, Types.VARCHAR);  // RG (null para ContaJuridica)
//            stmt.setNull(8, Types.DATE);     // dataNascimento (null para ContaJuridica)
//            stmt.setString(9, contaJuridica.getCnpj()); // Coluna CNPJ
//            stmt.setString(10, contaJuridica.getRazaoSocial()); // Coluna RazaoSocial
//            stmt.setString(11, contaJuridica.getNomeFantasia()); // Coluna NomeFantasia
//            stmt.setString(12, contaJuridica.getInscricaoEstadual()); // Coluna InscricaoEstadual
//        }
//    }
//
//
//
//    private Conta construirConta(ResultSet rs) throws SQLException {
//        TipoConta tipoConta = TipoConta.valueOf(rs.getString("tipoConta"));
//        if (tipoConta == TipoConta.F) {
//            return new ContaFisica(
//                    rs.getInt("id"),
//                    rs.getString("nome"),
//                    rs.getString("email"),
//                    rs.getString("numeroTelefone"),
//                    rs.getString("senha"),
//                    rs.getDate("dataNascimento").toLocalDate(),
//                    rs.getString("cpf"),
//                    rs.getString("rg")
//            );
//        } else if (tipoConta == TipoConta.J) {
//            return new ContaJuridica(
//                    rs.getInt("id"),
//                    rs.getString("nome"),
//                    rs.getString("email"),
//                    rs.getString("numeroTelefone"),
//                    rs.getString("senha"),
//                    rs.getString("cnpj"),
//                    rs.getString("razaoSocial"),
//                    rs.getString("nomeFantasia"),
//                    rs.getString("inscricaoEstadual")
//            );
//        }
//        return null;
//    }
}
