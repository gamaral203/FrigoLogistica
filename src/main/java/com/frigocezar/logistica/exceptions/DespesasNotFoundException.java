package com.frigocezar.logistica.exceptions;


public class DespesasNotFoundException extends RuntimeException {

    public DespesasNotFoundException() {super("Despesa Não encontrada");}

    public DespesasNotFoundException(String message) {
        super(message);
    }
}
