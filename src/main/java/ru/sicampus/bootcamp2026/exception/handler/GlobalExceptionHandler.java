package ru.sicampus.bootcamp2026.exception.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sicampus.bootcamp2026.exception.DepartmentNotFoundException;
import ru.sicampus.bootcamp2026.exception.PersonAlreadyExistsException;
import ru.sicampus.bootcamp2026.exception.PersonNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<String> handlerPersonAlreadyExistsException(PersonAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<String> handlerDepartmentNotFoundException(DepartmentNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<String> handlerPersonNotFoundException(PersonNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
