package br.cantaruttim.dataforge_api.controller.permissions.requests;

public record UpdatePermissionRequest(
    String name,
    String description
) {}