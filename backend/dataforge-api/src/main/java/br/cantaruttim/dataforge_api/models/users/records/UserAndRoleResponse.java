package br.cantaruttim.dataforge_api.models.users.records;

import java.util.UUID;

public record UserAndRoleResponse(
    UUID userId,
    String userName,
    UUID roleId,
    String roleName
) {}
