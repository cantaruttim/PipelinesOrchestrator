package br.cantaruttim.dataforge_api.exceptions.roles;

import java.util.UUID;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(UUID id) {
        super("Role not Found: " + id);
    }
    
}
