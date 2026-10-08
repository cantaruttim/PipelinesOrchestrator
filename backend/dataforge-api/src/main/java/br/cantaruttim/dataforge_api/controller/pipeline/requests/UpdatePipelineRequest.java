package br.cantaruttim.dataforge_api.controller.pipeline.requests;

public record UpdatePipelineRequest(
    String name,
    String description
) {}
