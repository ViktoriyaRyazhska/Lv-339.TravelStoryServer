package com.travelstory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String exceptionCause, String messageForUser, Class causeClass) {
        super(exceptionCause, messageForUser);
    }
}
