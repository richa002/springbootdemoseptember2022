package com.example.demo.error;

public class EntityNotFoundException extends  RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
