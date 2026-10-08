package br.cantaruttim.dataforge_api.models.pipelines.records;

import java.util.UUID;

public record PipelineAndUserResponse(
    UUID pipelineId,
    String pipelineName,
    UUID userId,
    String userName
) {}
