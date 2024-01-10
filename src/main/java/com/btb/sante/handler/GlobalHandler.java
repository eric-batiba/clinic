package com.btb.sante.handler;

import com.btb.sante.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler({
            CategoryNotFoundException.class, ServiceNotFoundException.class, NoResourceFoundException.class,
            PatientNoFoundException.class, ExamenNotFoundException.class
    })
    public ProblemDetail handlerNotFound(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(NOT_FOUND, e.getMessage());
        problemDetail.setProperty("error_code", NOT_FOUND);
        return problemDetail;
    }

    @ExceptionHandler({ServiceExistException.class})
    public ProblemDetail handlerConflict(Exception exception){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(CONFLICT, exception.getMessage());
        problemDetail.setProperty("error_code", CONFLICT );
        return problemDetail;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlerBadRequest(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return errors;
    }


}
