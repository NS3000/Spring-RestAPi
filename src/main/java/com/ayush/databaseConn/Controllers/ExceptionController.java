package com.ayush.databaseConn.Controllers;

import com.ayush.databaseConn.Exceptions.AddressNotFoundException;
import com.ayush.databaseConn.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> invalidArgumentsException(MethodArgumentNotValidException exception){
        List<String> errors = exception.getBindingResult().getFieldErrors().stream().map(x->x.getDefaultMessage()).toList();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException exception){
        return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(AddressNotFoundException exception){
        return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }
}
