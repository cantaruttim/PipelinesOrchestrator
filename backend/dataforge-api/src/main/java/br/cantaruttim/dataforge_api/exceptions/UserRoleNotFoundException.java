package br.cantaruttim.dataforge_api.exceptions;

import java.util.UUID;

public class UserRoleNotFoundException extends RuntimeException {
    
    public UserRoleNotFoundException(UUID id) {
        super("User-role not found! " + id);
    }    

}
