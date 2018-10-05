package com.travelstory.exceptions.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectDateException extends IncorrectInputDataException {
    public IncorrectDateException(String exceptionCause, String messageForUser) {
        super(exceptionCause, messageForUser);
    }
}
