// ContaService.java
package br.com.fiap.fintech.service;

import br.com.fiap.fintech.exceptions.CnpjInvalidoException;
import br.com.fiap.fintech.exceptions.ContaNaoEncontradaException;
import br.com.fiap.fintech.exceptions.CpfInvalidoException;
import br.com.fiap.fintech.exceptions.IdentificadorInvalidoException;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.ContaFisica;
import br.com.fiap.fintech.model.ContaJuridica;
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
        if (conta instanceof ContaFisica) {
            validarCPF(((ContaFisica) conta).getCpf());
        } else if (conta instanceof ContaJuridica) {
            validarCNPJ(((ContaJuridica) conta).getCnpj());
        }
        validarEmail(conta.getEmail());
        contaRepository.cadastrarConta(conta);
    }

    // Atualizar dados de uma conta existente
    public void atualizarConta(Conta conta) throws SQLException {
        if (conta.getId() == 0) {
            throw new IllegalArgumentException("ID da conta inválido para atualização.");
        }
        contaRepository.atualizarConta(conta);
    }

    // Buscar conta por ID com exceção customizada se não encontrada
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

    // Deletar uma conta pelo ID
    public void deletarConta(int id) throws SQLException {
        buscarContaPorId(id); // Certifica que a conta existe antes de deletar
        contaRepository.deletarConta(id);
    }

    // Login de usuário usando CPF ou CNPJ
    public Conta login(String identificador, String senha) throws SQLException {
        if (identificador.length() != 11 && identificador.length() != 14) {
            throw new IdentificadorInvalidoException("CPF deve ter 11 dígitos e CNPJ 14 dígitos.");
        }
        Conta conta = contaRepository.fazerLogin(identificador, senha);
        if (conta == null) {
            throw new IllegalArgumentException("Identificador ou senha inválidos.");
        }
        return conta;
    }

    // Validação de CPF com exceção customizada
    private void validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new CpfInvalidoException("CPF inválido. Deve ter 11 dígitos.");
        }
        // Outras validações de CPF podem ser incluídas aqui
    }

    // Validação de CNPJ com exceção customizada
    private void validarCNPJ(String cnpj) {
        if (cnpj == null || cnpj.length() != 14) {
            throw new CnpjInvalidoException("CNPJ inválido. Deve ter 14 dígitos.");
        }
        // Outras validações de CNPJ podem ser incluídas aqui
    }

    // Validação de e-mail
    private void validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        // Outras validações de e-mail podem ser incluídas aqui
    }
}
