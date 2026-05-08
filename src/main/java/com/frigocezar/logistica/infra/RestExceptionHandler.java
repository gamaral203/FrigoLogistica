package com.frigocezar.logistica.infra;

import com.frigocezar.logistica.exceptions.BusinessException;
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
    public ResponseEntity<String> motoristaNotFoundHandler(MotoristaNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Motorista não encontrado");
    }

    @ExceptionHandler(VeiculoNotFoundException.class)
    public ResponseEntity<String> veiculoNotFoundHandler(VeiculoNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo não encontrado");
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> businessExceptionHandler(BusinessException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

}
