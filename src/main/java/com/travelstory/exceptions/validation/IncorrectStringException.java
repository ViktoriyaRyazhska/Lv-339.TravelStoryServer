package com.travelstory.exceptions.validation;

import com.travelstory.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h2>Exception have to be thrown when string validation failed</h2>
 * <p>
 * All incorrect string exception codes has next template: <b>108*</b>
 * </p>
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectStringException extends IncorrectInputDataException {
    public IncorrectStringException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
