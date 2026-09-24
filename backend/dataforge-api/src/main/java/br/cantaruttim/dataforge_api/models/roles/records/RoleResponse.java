package br.cantaruttim.dataforge_api.models.roles.records;

import java.util.UUID;

public record RoleResponse(
    UUID id,
    String name, 
    String description
) {}
