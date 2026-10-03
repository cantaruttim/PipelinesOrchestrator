package br.cantaruttim.dataforge_api.repositories.pipeline;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cantaruttim.dataforge_api.models.pipelines.PipelineAndUser;

public interface PipelineAndUserRepository
    extends JpaRepository<PipelineAndUser, UUID> {

}