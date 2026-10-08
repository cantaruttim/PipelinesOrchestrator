package br.cantaruttim.dataforge_api.models.users.records;

import java.util.UUID;

public record UserAndPipelineResponse(
    UUID userId,
    String userName,
    UUID pipelineId,
    String pipelineName
) {}
