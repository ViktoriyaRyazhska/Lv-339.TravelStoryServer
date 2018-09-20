package com.travelstory.exceptions.security;

import com.travelstory.exceptions.RuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class SecurityException extends RuntimeException {
    public SecurityException(String exceptionCause, String messageForUser) {
        super(exceptionCause, messageForUser);
    }
}
