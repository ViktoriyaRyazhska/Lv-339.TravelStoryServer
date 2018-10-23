package com.travelstory.exceptions.api;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private Object rejectedMessage;
    private String message;

    public ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
