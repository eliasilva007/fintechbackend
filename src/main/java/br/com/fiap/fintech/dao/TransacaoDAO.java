package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.model.Transacao;

import java.util.Optional;

public interface TransacaoDAO {
    Optional<Transacao> findById(int id);
}
