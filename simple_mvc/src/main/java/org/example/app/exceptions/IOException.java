package org.example.app.exceptions;

public class IOException extends Exception{

    private final String message;

    public IOException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
