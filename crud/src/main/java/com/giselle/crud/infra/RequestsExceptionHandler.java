package com.giselle.crud.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestsExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO> threat404() {
        ExceptionDTO response = new ExceptionDTO("Dado nao encontrado com o ID fornecido", 404);
        return ResponseEntity.badRequest().body(response);
    }
}
