package com.mrizkyff.contactmanager.config;

import com.mrizkyff.contactmanager.exception.*;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseErrorDetail> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).toList();
        BaseErrorDetail errorDetail = new BaseErrorDetail(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors.toString(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( BadRequestException.class)
    public final ResponseEntity<BaseErrorDetail> handleBadRequestException(BadRequestException ex, WebRequest request) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( HttpMessageNotReadableException.class)
    public final ResponseEntity<BaseErrorDetail> handleMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( DataNotFoundException.class)
    public final ResponseEntity<BaseErrorDetail> handleDataNotFoundException(DataNotFoundException ex, WebRequest request) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler( PropertyReferenceException.class)
    public final ResponseEntity<BaseErrorDetail> handlePropertyReferenceException(PropertyReferenceException ex, WebRequest request) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler( UserNotFoundException.class)
//    public ResponseEntity<Map<String, List<String>>> handleNotFoundException(UserNotFoundException ex) {
//        List<String> errors = Collections.singletonList(ex.getMessage());
//        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<Map<String, List<String>>> handleGeneralExceptions(Exception ex) {
//        List<String> errors = Collections.singletonList(ex.getMessage());
//        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public final ResponseEntity<Map<String, List<String>>> handleRuntimeExceptions(RuntimeException ex) {
//        List<String> errors = Collections.singletonList(ex.getMessage());
//        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    private Map<String, List<String>> getErrorsMap(List<String> errors) {
//        Map<String, List<String>> errorResponse = new HashMap<>();
//        errorResponse.put("errors", errors);
//        return errorResponse;
//    }

}

