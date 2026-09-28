package br.cantaruttim.dataforge_api.models.users;

import java.util.UUID;

public record UserAndRoleResponse(
    UUID userId,
    String userName,
    UUID roleId,
    String roleDescription
) {}
