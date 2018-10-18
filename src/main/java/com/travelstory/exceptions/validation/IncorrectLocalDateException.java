package com.travelstory.exceptions.validation;

import com.travelstory.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h2>Exception have to be thrown when date/time validation failed</h2>
 * <p>
 * All incorrect date exception codes has next template: <b>107*</b>
 * </p>
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectLocalDateException extends IncorrectInputDataException {

    public IncorrectLocalDateException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
