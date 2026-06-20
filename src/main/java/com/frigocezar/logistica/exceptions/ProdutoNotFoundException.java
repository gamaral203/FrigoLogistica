package com.frigocezar.logistica.exceptions;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException() {
        super("Produto Não encontrado");
    }

    public ProdutoNotFoundException(String message) {
        super(message);
    }
}
