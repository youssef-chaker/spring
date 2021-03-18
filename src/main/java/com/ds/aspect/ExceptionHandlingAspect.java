package com.ds.aspect;

import com.ds.dto.ErrorDto;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import javassist.NotFoundException;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Aspect
@ControllerAdvice
public class ExceptionHandlingAspect {
    @ExceptionHandler
    public ResponseEntity<ErrorDto> notFound(NotFoundException e) {
        var error = new ErrorDto();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> nullpointer(NullPointerException e) {
        var error = new ErrorDto();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("please make sure all fileds do exist");
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> exception(Exception e) {
        var error = new ErrorDto();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> exception(InvalidFormatException e) {
        var error = new ErrorDto();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("some fields are not valid");
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
