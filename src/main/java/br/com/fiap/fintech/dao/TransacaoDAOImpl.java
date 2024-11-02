package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.factory.ConnectionFactory;
import br.com.fiap.fintech.model.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TransacaoDAOImpl implements TransacaoDAO {
    private final ConnectionFactory connectionFactory;

    public TransacaoDAOImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }


    @Override
    public Optional<Transacao> findById(int id) {
        String sql = "SELECT t.id_transacao, t.id_usuario, tr.valor_recebimento, tg.valor_gasto, tr.forma_recebimento, tg.forma_pagamento " +
                "FROM T_Transacao t " +
                "LEFT JOIN T_Transacao_Receita tr ON t.id_transacao = tr.id_transacao " +
                "LEFT JOIN T_Transacao_Gasto tg ON t.id_transacao = tg.id_transacao " +
                "WHERE t.id_transacao = ?";
        try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Transacao transacao = new Transacao();
                transacao.setId(resultSet.getInt("id_transacao"));
                transacao .setValor(resultSet.getDouble("valor_recebimento")!= 0
                        ?resultSet.getDouble("valor_recebimento")
                        :resultSet.getDouble("valor_gasto"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
