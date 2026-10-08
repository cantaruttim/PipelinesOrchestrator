package br.cantaruttim.dataforge_api.models.pipelines.records;

import java.util.UUID;

public record PipelineResponse(
    UUID id,
    String pipeName,
    String descriptions
) {}
