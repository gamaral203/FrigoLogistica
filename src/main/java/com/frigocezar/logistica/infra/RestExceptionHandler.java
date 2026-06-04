package com.frigocezar.logistica.infra;

import com.frigocezar.logistica.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MotoristaNotFoundException.class)
    public ResponseEntity<String> motoristaNotFoundHandler(MotoristaNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Motorista não encontrado");
    }

    @ExceptionHandler(VeiculoNotFoundException.class)
    public ResponseEntity<String> veiculoNotFoundHandler(VeiculoNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo não encontrado");
    }

    @ExceptionHandler(DespesasNotFoundException.class)
    public ResponseEntity<String> despesasNotFoundExceptionHandler(DespesasNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<String> duplicateResourceException(DuplicateResourceException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                erros.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericError(Exception ex) {
        log.error("Erro inesperado: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro interno. Tente novamente mais tarde.");
    }

}