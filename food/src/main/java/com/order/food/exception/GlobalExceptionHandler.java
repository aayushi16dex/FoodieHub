package com.order.food.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException e){
        String message = "Record not found";
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        ErrorResponse res = new ErrorResponse(message, errors, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<ErrorResponse> handleInvalidValueException(InvalidValueException e){
        String message = "Invalid value";
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        ErrorResponse res = new ErrorResponse(message, errors, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleIdNotFoundException(IdNotFoundException e){
        String message = "Invalid id";
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        ErrorResponse res = new ErrorResponse(message, errors, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @ExceptionHandler(RecordAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleRecordAlreadyExistsException(RecordAlreadyExistsException e){
        String message = "Already exists";
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        ErrorResponse res = new ErrorResponse(message, errors, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(InternalServerError e){
        String message = "INTERNAL SERVER ERROR";
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        ErrorResponse res = new ErrorResponse(message, errors, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<ErrorResponse> handleUnrecognizedField(UnrecognizedPropertyException e) {
        String message = "Unrecognized field";
        String fieldName = e.getPropertyName();
        String error = "Unrecognized field present: " + fieldName;
        List<String> errors = new ArrayList<>();
        errors.add(error);
        ErrorResponse res = new ErrorResponse(message, errors, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        String message = "Validation error";
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.add(error.getDefaultMessage()));
        ErrorResponse res = new ErrorResponse(message, errors, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundErrors(NoResourceFoundException ex) {
        String message = "Resource not found";
        String error = ex.getLocalizedMessage();
        List<String> errors = new ArrayList<>();
        errors.add(error);
        ErrorResponse res = new ErrorResponse(message, errors, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchErrors(MethodArgumentTypeMismatchException ex) {
        String message = "Wrong arguments";
        String error = ex.getLocalizedMessage();
        List<String> errors = new ArrayList<>();
        errors.add(error);
        ErrorResponse res = new ErrorResponse(message, errors, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
