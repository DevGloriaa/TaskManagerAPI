package com.example.taskmanagerapi.exceptions;

public class HandleUserDoesNotExistException extends RuntimeException{
    public HandleUserDoesNotExistException(String message) {
        super(message);
    }
}
