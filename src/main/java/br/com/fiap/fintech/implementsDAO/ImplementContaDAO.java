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

public class ImplementContaDAO implements ContaDAO {
    private Connection connection;

    public ImplementContaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void cadastrar(Conta conta) throws SQLException {
        String sql = "INSERT INTO T_Conta (nome, email, numeroTelefone, senha, tipoConta, cpf, rg, dataNascimento, cnpj, razaoSocial, nomeFantasia, inscricaoEstadual) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, conta.getNome());
            stmt.setString(2, conta.getEmail());
            stmt.setString(3, conta.getNumeroTelefone());
            stmt.setString(4, conta.getSenha());
            stmt.setString(5, conta.getTipoConta().name());

            if (conta instanceof ContaFisica) {
                ContaFisica contaFisica = (ContaFisica) conta;
                stmt.setString(6, contaFisica.getCpf());
                stmt.setString(7, contaFisica.getRg());
                stmt.setDate(8, java.sql.Date.valueOf(contaFisica.getDataNascimento()));
                // Definindo valores nulos para os atributos de ContaJuridica
                stmt.setNull(9, java.sql.Types.VARCHAR); // CNPJ
                stmt.setNull(10, java.sql.Types.VARCHAR); // Razão Social
                stmt.setNull(11, java.sql.Types.VARCHAR); // Nome Fantasia
                stmt.setNull(12, java.sql.Types.VARCHAR); // Inscrição Estadual
            } else if (conta instanceof ContaJuridica) {
                ContaJuridica contaJuridica = (ContaJuridica) conta;
                stmt.setNull(6, java.sql.Types.VARCHAR); // CPF
                stmt.setNull(7, java.sql.Types.VARCHAR); // RG
                stmt.setNull(8, java.sql.Types.DATE);    // Data de Nascimento
                stmt.setString(9, contaJuridica.getCnpj());
                stmt.setString(10, contaJuridica.getRazaoSocial());
                stmt.setString(11, contaJuridica.getNomeFantasia());
                stmt.setString(12, contaJuridica.getInscricaoEstadual());
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public Conta fazerLogin(String identificador, String senha) throws SQLException {
        // Verificação de comprimento do identificador
        if (identificador.length() != 11 && identificador.length() != 14) {
            throw new IdentificadorInvalidoException("Identificador inválido. CPF deve ter 11 dígitos e CNPJ 14 dígitos.");
        }

        String sql;
        boolean isCPF = identificador.length() == 11;

        if (isCPF) {
            sql = "SELECT * FROM T_Conta WHERE cpf = ? AND senha = ?";
        } else {
            sql = "SELECT * FROM T_Conta WHERE cnpj = ? AND senha = ?";
        }

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
        }
        return null; // Retorna null se não encontrar a conta
    }


    @Override
    public Conta buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM T_Conta WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String tipoContaStr = rs.getString("tipoConta");
                    TipoConta tipoConta = TipoConta.valueOf(tipoContaStr);

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
        }
        return null;
    }

    @Override
    public List<Conta> listarTodas() throws SQLException {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT * FROM T_Conta";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String tipoContaStr = rs.getString("tipoConta");
                TipoConta tipoConta = TipoConta.valueOf(tipoContaStr);

                if (tipoConta == TipoConta.FISICA) {
                    ContaFisica contaFisica = new ContaFisica(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("numeroTelefone"),
                            rs.getString("senha"),
                            rs.getDate("dataNascimento").toLocalDate(),
                            rs.getString("cpf"),
                            rs.getString("rg")
                    );
                    contas.add(contaFisica);
                } else if (tipoConta == TipoConta.JURIDICA) {
                    ContaJuridica contaJuridica = new ContaJuridica(
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
                    contas.add(contaJuridica);
                }
            }
        }
        return contas;
    }

    @Override
    public void atualizar(Conta conta) throws SQLException {
        String sql = "UPDATE T_Conta SET nome = ?, email = ?, numeroTelefone = ?, senha = ?, tipoConta = ?, cpf = ?, rg = ?, dataNascimento = ?, cnpj = ?, razaoSocial = ?, nomeFantasia = ?, inscricaoEstadual = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, conta.getNome());
            stmt.setString(2, conta.getEmail());
            stmt.setString(3, conta.getNumeroTelefone());
            stmt.setString(4, conta.getSenha());
            stmt.setString(5, conta.getTipoConta().name());

            if (conta instanceof ContaFisica) {
                ContaFisica contaFisica = (ContaFisica) conta;
                stmt.setString(6, contaFisica.getCpf());
                stmt.setString(7, contaFisica.getRg());
                stmt.setDate(8, java.sql.Date.valueOf(contaFisica.getDataNascimento()));
                stmt.setNull(9, java.sql.Types.VARCHAR);
                stmt.setNull(10, java.sql.Types.VARCHAR);
                stmt.setNull(11, java.sql.Types.VARCHAR);
                stmt.setNull(12, java.sql.Types.VARCHAR);
            } else if (conta instanceof ContaJuridica) {
                ContaJuridica contaJuridica = (ContaJuridica) conta;
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
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM T_Conta WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
