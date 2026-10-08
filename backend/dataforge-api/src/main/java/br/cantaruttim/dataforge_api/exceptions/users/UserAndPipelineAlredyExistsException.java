package br.cantaruttim.dataforge_api.exceptions.users;

import java.util.UUID;

public class UserAndPipelineAlredyExistsException extends RuntimeException {

    public UserAndPipelineAlredyExistsException(
        UUID pipelineId,
        UUID userId    
    ) {
        super(
            "User " + userId +
            " is already assigned to this role " + pipelineId
        );
    }
    
}
