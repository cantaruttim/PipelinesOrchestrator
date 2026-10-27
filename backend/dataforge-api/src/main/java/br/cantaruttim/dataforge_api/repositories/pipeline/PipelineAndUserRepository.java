package br.cantaruttim.dataforge_api.repositories.pipeline;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.cantaruttim.dataforge_api.models.pipelines.PipelineAndUser;
import br.cantaruttim.dataforge_api.models.users.records.UserResponse;

public interface PipelineAndUserRepository 
    extends JpaRepository<PipelineAndUser, UUID> { 

    boolean existsByPipelineIdAndUserId(
        UUID pipelineId,
        UUID userId
    );

    List<PipelineAndUser> findByUserId(UUID userId);

    List<PipelineAndUser> findByPipelineId(UUID pipelineId);

    @Query("""
       SELECT 
            new br.cantaruttim.dataforge_api.models.users.records.UserResponse(
                u.id,
                u.userName,
                u.userEmail
       )
        FROM PipelineAndUser pu
        JOIN pu.user u
        WHERE pu.pipeline.id = :pipelineId
    """)
    List<UserResponse> findUserResponsesByPipelineId(
        @Param("pipelineId") UUID pipelineId
    );

}
