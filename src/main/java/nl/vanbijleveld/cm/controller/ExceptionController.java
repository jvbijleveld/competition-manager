package nl.vanbijleveld.cm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.exception.ConflictException;
import nl.vanbijleveld.cm.exception.ExceptionResponse;

@RestController
public class ExceptionController {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<?> handleGeneralException(Exception exception, final HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse(exception, HttpStatus.SERVICE_UNAVAILABLE);
        return new ResponseEntity<ExceptionResponse>(response, response.getStatusCode());
    }
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(final NotFoundException exception, final HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse(exception, HttpStatus.NOT_FOUND);
        return new ResponseEntity<ExceptionResponse>(response, response.getStatusCode());
    }

    @ResponseBody
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflictException(final ConflictException exception, final HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse(exception, HttpStatus.CONFLICT);
        return new ResponseEntity<ExceptionResponse>(response, response.getStatusCode());
    }

}
