package com.travelstory.exceptions.api;

import java.util.Objects;

public class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private Object rejectedMessage;
    private String message;

    public ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getRejectedMessage() {
        return rejectedMessage;
    }

    public void setRejectedMessage(Object rejectedMessage) {
        this.rejectedMessage = rejectedMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiValidationError(String object, String field, Object rejectedMessage, String message) {
        this.object = object;
        this.field = field;
        this.rejectedMessage = rejectedMessage;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiValidationError that = (ApiValidationError) o;
        return Objects.equals(object, that.object) && Objects.equals(field, that.field)
                && Objects.equals(rejectedMessage, that.rejectedMessage) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(object, field, rejectedMessage, message);
    }
}
