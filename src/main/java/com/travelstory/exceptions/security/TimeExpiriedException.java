package com.travelstory.exceptions.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class TimeExpiriedException extends SecurityException {
    public TimeExpiriedException(String exceptionCause, String messageForUser) {
        super(exceptionCause, messageForUser);
    }
}
