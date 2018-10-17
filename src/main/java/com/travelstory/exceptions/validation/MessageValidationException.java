package com.travelstory.exceptions.validation;

import com.travelstory.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h2>Exception have to be thrown when message validation failed</h2>
 * <p>
 * All message exception codes has next template: <b>109*</b>
 * </p>
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MessageValidationException extends IncorrectInputDataException {
    public MessageValidationException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
