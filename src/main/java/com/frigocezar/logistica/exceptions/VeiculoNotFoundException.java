package com.frigocezar.logistica.exceptions;

public class VeiculoNotFoundException extends RuntimeException {

    public VeiculoNotFoundException() {
        super("Veiculo Não encontrado");
    }

    public VeiculoNotFoundException(String message) {
        super(message);
    }


}
