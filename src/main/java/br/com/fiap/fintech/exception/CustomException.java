package br.com.fiap.fintech.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message){
        super(message);
    }
}
