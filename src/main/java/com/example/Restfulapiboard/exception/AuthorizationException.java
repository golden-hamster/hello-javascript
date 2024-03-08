package com.example.Restfulapiboard.exception;

public class AuthorizationException extends RuntimeException{

    private static final String MESSAGE = "권한이 없습니다.";

    public AuthorizationException() {
        super(MESSAGE);
    }

    public AuthorizationException(String message) {super(message);}
}
