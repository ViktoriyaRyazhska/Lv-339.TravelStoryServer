package com.travelstory.exceptions.security;

import com.travelstory.exceptions.TravelStoryAppException;
import com.travelstory.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h2>Exception have to be thrown when it's unauthorized request</h2>
 * <p>
 * All access denied exception codes has next template <b>73**</b>
 * </p>
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AccessDeniedException extends TravelStoryAppException {
    public AccessDeniedException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
