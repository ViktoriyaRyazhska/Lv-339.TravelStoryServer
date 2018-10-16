package com.travelstory.exceptions;

import com.travelstory.exceptions.codes.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@ToString
public class TravelStoryAppException extends RuntimeException {
    private String message;
    private ExceptionCode exceptionCode;
}
