package br.cantaruttim.dataforge_api.exceptions;

public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(String msg) {
        super(msg);
    }

}
