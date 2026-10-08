package br.cantaruttim.dataforge_api.exceptions.users;

import java.util.UUID;

public class UserRoleAlreadyExistsException extends RuntimeException {
    
    public UserRoleAlreadyExistsException(
        UUID userId,    
        UUID roleId
    ) {
        super(
            "User " + userId +
            " is already assigned to this role " + roleId
        );
    }
}
