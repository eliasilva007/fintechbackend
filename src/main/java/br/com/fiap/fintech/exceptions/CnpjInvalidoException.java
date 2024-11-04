package br.com.fiap.fintech.exceptions;

public class CnpjInvalidoException extends RuntimeException {
    public CnpjInvalidoException(String message) {
        super(message);
    }
}
