package com.travelstory.exceptions;

import com.travelstory.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h2>General exception for validation</h2>
 * <p>
 * All resource not found exception codes has next template: <b>20**</b>
 * </p>
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends TravelStoryAppException {

    public ResourceNotFoundException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
