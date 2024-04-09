package com.example.pokerpage.exception;

import com.example.pokerpage.enums.ErrorEnum;
import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException{
    private ErrorEnum error;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, ErrorEnum err) {
        super(message);
        this.error = err;
    }
}