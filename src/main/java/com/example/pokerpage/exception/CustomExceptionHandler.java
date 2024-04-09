package com.example.pokerpage.exception;

import com.example.pokerpage.enums.ErrorEnum;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(DataException.class)
    public ResponseEntity<String> handleDataException(DataException ex){
        ex.printStackTrace();
        if (ex.getError() == ErrorEnum.NOT_FOUND){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        String errorMessage = "Erro: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.badRequest().body("Ocorreu um erro ao realizar a requisição!");
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<String> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex){
        String errorMessage = "Erro: Nome inserido não existe!";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}