package br.cantaruttim.dataforge_api.exceptions.handlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.cantaruttim.dataforge_api.exceptions.UserNotFoundException;
import br.cantaruttim.dataforge_api.exceptions.ApiErrorResponse;
import br.cantaruttim.dataforge_api.exceptions.RolePermissionNotFoundException;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotFound(
            UserNotFoundException exception
    ) {

        ApiErrorResponse response = new ApiErrorResponse(
                404,
                "USER_NOT_FOUND",
                exception.getMessage(),
                LocalDateTime.now(),
                Map.of() // sem erros específicos de campos
        );

        return ResponseEntity
                .status(404)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException exception
    ) {

        Map<String, String> fields = new HashMap<>();

        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(error -> 
                        // campos específicos de erros
                        fields.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        ApiErrorResponse response = new ApiErrorResponse(
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


    @ExceptionHandler(RolePermissionNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleRolePermissionNotFound(
        RolePermissionNotFoundException exception
    ) {
        ApiErrorResponse response = new ApiErrorResponse(
                404,
                "NOT_FOUND",
                exception.getMessage(),
                LocalDateTime.now(),
                Map.of()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    
}