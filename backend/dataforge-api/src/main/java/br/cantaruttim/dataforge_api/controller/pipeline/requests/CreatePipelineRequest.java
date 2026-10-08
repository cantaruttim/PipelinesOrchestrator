package br.cantaruttim.dataforge_api.controller.pipeline.requests;

import jakarta.validation.constraints.NotBlank;

public record CreatePipelineRequest(
    @NotBlank String name,
    @NotBlank String description
) {}
