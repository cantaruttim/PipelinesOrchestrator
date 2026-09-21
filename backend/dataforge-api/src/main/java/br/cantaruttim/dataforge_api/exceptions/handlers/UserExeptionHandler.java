package br.cantaruttim.dataforge_api.exceptions.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.cantaruttim.dataforge_api.exceptions.UserNotFoundException;

@RestControllerAdvice 
public class UserExeptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(
        UserNotFoundException exception
    ) {
        return ResponseEntity
                .status(404)
                .body(
                    exception.getMessage()
                );
    }

}
