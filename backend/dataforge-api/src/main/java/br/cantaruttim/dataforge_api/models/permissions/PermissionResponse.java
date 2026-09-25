package br.cantaruttim.dataforge_api.models.permissions;

import java.util.UUID;

public record PermissionResponse(
    UUID permissionId,
    String permissionName
) {}
