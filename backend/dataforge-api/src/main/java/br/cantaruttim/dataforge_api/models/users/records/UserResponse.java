package br.cantaruttim.dataforge_api.models.users.records;

import java.util.UUID;

public record UserResponse(
    UUID id,
    String userName,
    String userEmail
) {}
