package org.example.app.exceptions;

public class DownloadsPageException extends Exception{

    private String message;

    public DownloadsPageException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
