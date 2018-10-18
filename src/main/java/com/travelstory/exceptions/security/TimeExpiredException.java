package com.travelstory.exceptions.security;

import com.travelstory.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h2>Exception have to be thrown when it's token expiration time is left</h2>
 * <p>
 * All time expired exception codes has next template <b>72**</b>
 * </p>
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class TimeExpiredException extends SecurityException {

    public TimeExpiredException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
