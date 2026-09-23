package br.cantaruttim.dataforge_api.exceptions;

import java.util.UUID;

public class PermissionNotFoundException extends RuntimeException {

    public PermissionNotFoundException(UUID id) {
        super("Permission not found: " + id);
    }
}