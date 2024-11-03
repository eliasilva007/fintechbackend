package br.com.fiap.fintech.exceptions;

public class IdentificadorInvalidoException extends RuntimeException {
    public IdentificadorInvalidoException(String mensagem) {
        super(mensagem);
    }
}
