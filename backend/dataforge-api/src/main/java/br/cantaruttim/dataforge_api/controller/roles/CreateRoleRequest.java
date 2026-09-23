package br.cantaruttim.dataforge_api.controller.roles;

import jakarta.validation.constraints.NotBlank;

public record CreateRoleRequest(
    @NotBlank String name,
    @NotBlank String description
) {}
