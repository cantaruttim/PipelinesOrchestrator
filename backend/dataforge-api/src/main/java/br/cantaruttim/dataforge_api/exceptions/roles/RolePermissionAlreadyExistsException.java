package br.cantaruttim.dataforge_api.exceptions.roles;

import java.util.UUID;

public class RolePermissionAlreadyExistsException extends RuntimeException {

    public RolePermissionAlreadyExistsException(
            UUID roleId,
            UUID permissionId
    ) {
        super(
            "Permission " + permissionId +
            " is already assigned to role " + roleId
        );
    }
    
}
