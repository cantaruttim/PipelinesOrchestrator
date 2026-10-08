package br.cantaruttim.dataforge_api.repositories.pipeline;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cantaruttim.dataforge_api.models.pipelines.PipelineAndUser;

public interface PipelineAndUserRepository 
    extends JpaRepository<PipelineAndUser, UUID> { 

    boolean existsByPipelineIdAndUserId(
        UUID pipelineId,
        UUID userId
    );

    List<PipelineAndUser> findByUserId(UUID userId);

    List<PipelineAndUser> findByPipelineId(UUID roleId);

}
