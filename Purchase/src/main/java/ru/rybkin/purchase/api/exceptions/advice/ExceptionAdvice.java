package ru.rybkin.purchase.api.exceptions.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.rybkin.purchase.api.exceptions.NotFoundException;
import ru.rybkin.purchase.api.exceptions.RequestClientException;
import ru.rybkin.purchase.api.exceptions.RequestServerException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<Map<String, String>> notFoundHandler(NotFoundException ex) {
        Map<String, String> e = new HashMap<>();
        log.error(ex.getMessage());
        e.put("error", ex.getClass().getSimpleName());
        e.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Map<String, String>> apiException(ConstraintViolationException ex) {
        Map<String, String> e = new HashMap<>();
        log.error(ex.getMessage());
        e.put("error", ex.getClass().getSimpleName());
        e.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String, String>> methodArgumentValidationHandler(MethodArgumentNotValidException ex) {
        Map<String, String> e = new HashMap<>();
        log.error(ex.getMessage());
        e.put("error", ex.getClass().getSimpleName());
        e.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }

    @ResponseBody
    @ExceptionHandler(RequestClientException.class)
    ResponseEntity<Map<String, String>> requestClientExceptionHandler(RequestClientException ex) {
        Map<String, String> e = new HashMap<>();
        log.error(ex.getMessage());
        e.put("error", ex.getClass().getSimpleName());
        e.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }

    @ResponseBody
    @ExceptionHandler(RequestServerException.class)
    ResponseEntity<Map<String, String>> requestServerExceptionHandler(RequestServerException ex) {
        Map<String, String> e = new HashMap<>();
        log.error(ex.getMessage());
        e.put("error", ex.getClass().getSimpleName());
        e.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }
}
