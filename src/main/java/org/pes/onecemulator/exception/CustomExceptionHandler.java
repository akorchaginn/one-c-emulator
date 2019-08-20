package org.pes.onecemulator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleAllExceptions(final Exception e) {
        return new ResponseEntity<>(new ApiError(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ApiError> handleNotFoundExceptions(final Exception e) {
        return new ResponseEntity<>(new ApiError(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<ApiError> handleValidationExceptions(final Exception e) {
        return new ResponseEntity<>(new ApiError(e), HttpStatus.BAD_REQUEST);
    }
}
