package br.cantaruttim.dataforge_api.exceptions.users;

public class UserAndPipelineNotFoundException extends RuntimeException {
    
    public UserAndPipelineNotFoundException(String msg) {
        super(msg);
    }
}
