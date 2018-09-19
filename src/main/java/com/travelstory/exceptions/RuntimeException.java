package com.travelstory.exceptions;

public class RuntimeException extends java.lang.RuntimeException {
    private String message;
    private String messageForUser;

    public RuntimeException(String message, String messageForUser) {
        this.message = message;
        this.messageForUser = messageForUser;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageForUser() {
        return messageForUser;
    }

    public void setMessageForUser(String messageForUser) {
        this.messageForUser = messageForUser;
    }
}
