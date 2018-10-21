package com.travelstory.exceptions.security;

import com.travelstory.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h2>Exception have to be thrown when it's token validation failed</h2>
 * <p>
 * All token validation exception codes has next template <b>71**</b>
 * </p>
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class TokenValidationException extends SecurityException {
    public TokenValidationException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
