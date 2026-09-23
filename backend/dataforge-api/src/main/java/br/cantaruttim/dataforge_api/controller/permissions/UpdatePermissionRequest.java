package br.cantaruttim.dataforge_api.controller.permissions;

public record UpdatePermissionRequest(
    String name,
    String description
) {}