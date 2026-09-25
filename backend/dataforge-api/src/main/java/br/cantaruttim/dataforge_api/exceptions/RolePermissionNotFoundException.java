package br.cantaruttim.dataforge_api.exceptions;

import java.util.UUID;

public class RolePermissionNotFoundException extends RuntimeException {
    
    public RolePermissionNotFoundException(UUID id) {
        super("Role-permission not found! " + id);
    }

}
