package br.cantaruttim.dataforge_api.controller.permissions;

import jakarta.validation.constraints.NotBlank;

public record CreatePermissionRequest(
    @NotBlank String name,
    @NotBlank String description
) {}
