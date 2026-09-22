package br.cantaruttim.dataforge_api.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiErrorResponse(
    Integer status,
    String error,
    String message, 
    LocalDateTime timestamp,
    Map<String, String> fields
) {}
