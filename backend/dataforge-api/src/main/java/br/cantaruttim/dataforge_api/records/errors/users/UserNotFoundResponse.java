package br.cantaruttim.dataforge_api.records.errors.users;

import java.time.LocalDateTime;

public record UserNotFoundResponse(
    // será transformado em json pelo Jackson/Spring
    Integer status,
    String error,
    String message, 
    LocalDateTime timestamp
) {}
