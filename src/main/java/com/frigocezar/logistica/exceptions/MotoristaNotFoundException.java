package com.frigocezar.logistica.exceptions;

public class MotoristaNotFoundException extends RuntimeException {

    public MotoristaNotFoundException() {super("Motorista não encontrado");}


    public MotoristaNotFoundException(String message) {
        super(message);
    }
}
