package com.travelstory.exceptions.validation;

import com.travelstory.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h2>Exception have to be thrown when email validation failed</h2>
 * <p>
 * All incorrect email exception codes has next template: <b>105*</b>
 * </p>
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectEmailException extends IncorrectInputDataException {
    public IncorrectEmailException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
