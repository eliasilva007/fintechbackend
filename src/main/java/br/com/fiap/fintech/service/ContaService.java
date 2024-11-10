// ContaService.java
package br.com.fiap.fintech.service;

import br.com.fiap.fintech.exceptions.ContaNaoEncontradaException;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.repository.ContaRepository;

import java.sql.SQLException;
import java.util.List;

public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    // Cadastrar nova conta com validações específicas
    public void cadastrarConta(Conta conta) throws SQLException {
        validarEmail(conta.getEmail());
        contaRepository.cadastrarConta(conta);
    }

    // Atualizar dados de uma conta existente
//    public void atualizarConta(Conta conta) throws SQLException {
//        if (conta.getId() == 0) {
//            throw new IllegalArgumentException("ID da conta inválido para atualização.");
//        }
//        contaRepository.atualizarConta(conta);
//    }

//     Buscar conta por ID com exceção customizada se não encontrada
    public Conta buscarContaPorId(int id) throws SQLException {
        Conta conta = contaRepository.buscarContaPorId(id);
        if (conta == null) {
            throw new ContaNaoEncontradaException("Conta não encontrada para o ID fornecido.");
        }
        return conta;
    }

    // Listar todas as contas
    public List<Conta> listarTodasContas() throws SQLException {
        return contaRepository.listarTodasContas();
    }


    public void deletarConta(int id) throws SQLException {
        // Certifica que a conta existe antes de deletar
        contaRepository.deletarConta(id);
    }

    // Login de usuário usando CPF ou CNPJ
    public Conta login(String identificador, String senha) throws SQLException {

        Conta conta = contaRepository.fazerLogin(identificador, senha);
        if (conta == null) {
            throw new IllegalArgumentException("Identificador ou senha inválidos.");
        }
        return conta;
    }

    // Validação de e-mail
    private void validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        // Outras validações de e-mail podem ser incluídas aqui
    }
}
