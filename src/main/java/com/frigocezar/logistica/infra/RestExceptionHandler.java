package com.frigocezar.logistica.infra;

import com.frigocezar.logistica.exceptions.MotoristaNotFoundException;
import com.frigocezar.logistica.exceptions.VeiculoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MotoristaNotFoundException.class)
    private ResponseEntity<String> motoristaNotFoundHandler(MotoristaNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Motorista não encontrado");
    }

    @ExceptionHandler(VeiculoNotFoundException.class)
    private ResponseEntity<String> veiculoNotFoundHandler(VeiculoNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo não encontrado");
    }

}
