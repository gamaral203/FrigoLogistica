package com.frigocezar.logistica.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException() {
        super("Placa Já cadastrada");
    }

    public BusinessException(String message) {
        super(message);
    }
}
