package com.travelstory.exceptions.validation;

import com.travelstory.exceptions.RuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    public ValidationException(String exceptionCause, String messageForUser) {
        super(exceptionCause, messageForUser);
    }
}
