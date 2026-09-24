package br.cantaruttim.dataforge_api.models.roles.records;

import java.util.UUID;

public record RoleAndPermissionResponse(
    UUID roleId,
    String roleName,
    UUID permissionId,
    String permissionName
) {}
