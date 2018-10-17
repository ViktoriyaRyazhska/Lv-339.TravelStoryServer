package com.travelstory.exceptions.api;

import com.travelstory.exceptions.TravelStoryAppException;
import com.travelstory.exceptions.codes.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Main exception handler for all expected exceptions in TravelStoryAPI
     *
     * @param ex
     *            exception
     * @param request
     *            web request
     * @return the ResponseEntity
     */
    @ExceptionHandler(TravelStoryAppException.class)
    protected ResponseEntity<HashMap<String, Object>> handleCustomRuntimeException(TravelStoryAppException ex,
            WebRequest request) {
        ApiError apiError = new ApiError();
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            apiError.setStatus(responseStatus.code());
        }
        log.error(ex.getMessage(), ex);

        apiError.setDebugMessage(ex.getMessage());
        apiError.setExceptionCode(ex.getExceptionCode().exceptionCode);
        apiError.setTimestamp(LocalDateTime.now());

        HashMap<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("error", apiError);

        return new ResponseEntity<HashMap<String, Object>>(jsonBody, apiError.getStatus());

    }

    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex
     *            MissingServletRequestParameterException
     * @param headers
     *            HttpHeaders
     * @param status
     *            HttpStatus
     * @param request
     *            WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorText = ex.getParameterName() + " parameter is missing";
        log.error(errorText);
        log.error(ex.getMessage());
        return buildResponseEntity(
                new ApiError(BAD_REQUEST, ExceptionCode.MISSING_SERVLET_REQUEST_PARAMETER.exceptionCode, errorText));
    }

    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
     *
     * @param ex
     *            HttpMediaTypeNotSupportedException
     * @param headers
     *            HttpHeaders
     * @param status
     *            HttpStatus
     * @param request
     *            WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        String errorText = builder.substring(0, builder.length() - 2);

        log.error(errorText);
        log.error(ex.getMessage());

        return buildResponseEntity(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                ExceptionCode.UNSUPPORTED_MEDIA_TYPE.exceptionCode, errorText));
    }

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param ex
     *            the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @param headers
     *            HttpHeaders
     * @param status
     *            HttpStatus
     * @param request
     *            WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setStatus(BAD_REQUEST);
        apiError.setDebugMessage("Validation error");
        apiError.setExceptionCode(ExceptionCode.VALIDATION_FAILED.exceptionCode);
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());

        log.error("Validation error in handleMethodArgumentNotValid exception handler");
        log.error(ex.getMessage());

        return buildResponseEntity(apiError);
    }

    /**
     * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
     *
     * @param ex
     *            the ConstraintViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
        ApiError apiError = new ApiError();
        apiError.setStatus(BAD_REQUEST);
        apiError.setExceptionCode(ExceptionCode.VALIDATION_FAILED.exceptionCode);
        apiError.setDebugMessage("Validation error");
        apiError.addValidationErrors(ex.getConstraintViolations());

        log.error("Validation error in handleConstraintViolation exception handler");
        log.error(ex.getMessage());

        return buildResponseEntity(apiError);
    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param ex
     *            HttpMessageNotReadableException
     * @param headers
     *            HttpHeaders
     * @param status
     *            HttpStatus
     * @param request
     *            WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String errorText = "Malformed JSON request";
        return buildResponseEntity(new ApiError(BAD_REQUEST, ExceptionCode.JSON_IS_MALFORMED.exceptionCode, errorText));
    }

    /**
     * Handle NoHandlerFoundException.
     *
     * @param ex
     *            NoHandlerFoundException
     * @param headers
     *            HttpHeaders
     * @param status
     *            HttpStatus
     * @param request
     *            WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ApiError apiError = new ApiError(BAD_REQUEST, ExceptionCode.NO_EXCEPTION_HANDLER.exceptionCode,
                String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));

        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<Object>(apiError, apiError.getStatus());
    }

}