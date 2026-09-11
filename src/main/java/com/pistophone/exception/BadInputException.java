package com.pistophone.exception;

public class BadInputException extends RuntimeException {
    public BadInputException() {
        super("Input must be one letter of the Cyrillic alphabet");
    }
    public BadInputException(String message) {
        super(message);
    }
}
