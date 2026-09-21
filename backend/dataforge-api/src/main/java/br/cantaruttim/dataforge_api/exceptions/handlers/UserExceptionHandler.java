package br.cantaruttim.dataforge_api.exceptions.handlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.cantaruttim.dataforge_api.records.errors.users.ValidationUserResponse;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationUserResponse> handleValidation(
            MethodArgumentNotValidException exception
    ) {

        Map<String, String> fields = new HashMap<>();

        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        fields.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        ValidationUserResponse response =
                new ValidationUserResponse(
                        400,
                        "VALIDATION_ERROR",
                        "Request contains invalid fields",
                        LocalDateTime.now(),
                        fields
                );

        return ResponseEntity
                .status(400)
                .body(response);
    }
}