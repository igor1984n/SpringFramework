package org.example.app.exceptions;

public class CannotAccessFileException extends Exception {

    private final String message;
    public CannotAccessFileException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
