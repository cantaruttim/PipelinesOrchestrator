package br.cantaruttim.dataforge_api.records.errors.users;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationUserResponse(
    Integer status,
    String error,
    String message,
    LocalDateTime timestamp,
    Map<String, String> fields // objeto json
) {}
