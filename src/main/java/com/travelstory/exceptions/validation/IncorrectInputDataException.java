package com.travelstory.exceptions.validation;

import com.travelstory.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h2>General exception for incorrect input data</h2>
 * <p>
 * All validation exception codes has next template: <b>10**</b>
 * </p>
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectInputDataException extends ValidationException {

    public IncorrectInputDataException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
