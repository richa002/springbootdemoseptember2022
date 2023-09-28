package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class MyControllerAdvice {

    //{
    //   message : "Customer not found",
    //   errorCode : 10001
    // }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage>  exceptionHandler(EntityNotFoundException ex){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),1001);
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage>  exceptionHandler2(Exception ex){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),1002);
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage>  exceptionHandler2(MethodArgumentNotValidException ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(ex.getBindingResult().getAllErrors().stream().map(x->x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
        errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return  new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

    }
}
