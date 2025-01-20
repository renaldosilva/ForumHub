package com.forumhub.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ErrorManager {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity manageError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity manageError400(MethodArgumentNotValidException exception) {
        var erros = exception.getFieldErrors().stream().map(DadosErrosValidacaoDTO::new).toList();

        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity manageError400(HttpMessageNotReadableException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity handleDuplicateEntry(SQLIntegrityConstraintViolationException exception) {
        String message = exception.getMessage();

        if (message.contains("Duplicate entry")) {
            return ResponseEntity.badRequest().body("Há um tópico semelhante em uso!");
        }

        return ResponseEntity.status(500).body("Erro interno no servidor.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity illegalArguments(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    private record DadosErrosValidacaoDTO(String campo, String mensagem) {
        public DadosErrosValidacaoDTO(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
