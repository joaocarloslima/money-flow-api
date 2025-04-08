package br.com.fiap.money_flow_api.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {

    record ValidationError(String field, String message){
        public ValidationError(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationError> handle(MethodArgumentNotValidException e){
        return e.getFieldErrors()
                    .stream()
                    .map(ValidationError::new) //reference method
                    .toList();

    }
    
}
