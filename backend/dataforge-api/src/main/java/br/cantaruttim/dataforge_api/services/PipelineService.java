package br.cantaruttim.dataforge_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.cantaruttim.dataforge_api.exceptions.pipelines.PipelineNotFoundException;
import br.cantaruttim.dataforge_api.models.pipelines.Pipeline;
import br.cantaruttim.dataforge_api.models.pipelines.records.PipelineResponse;
import br.cantaruttim.dataforge_api.repositories.pipeline.PipelineRepository;

@Service
public class PipelineService {

    private final PipelineRepository pipelineRepository;

    public PipelineService(PipelineRepository pipelineRepository) {
        this.pipelineRepository = pipelineRepository;
    }

    public PipelineResponse create(
        String name,
        String description
    ) {

        Pipeline pipeline = new Pipeline(
            UUID.randomUUID(),
            name,
            description
        );

        Pipeline savedPipeline = pipelineRepository.save(pipeline);

        return new PipelineResponse(
            savedPipeline.getId(),
            savedPipeline.getPipeName(),
            savedPipeline.getDescription()
        );
    }

    public PipelineResponse getById(UUID id) {

        Pipeline pipeline = pipelineRepository
            .findById(id)
            .orElseThrow(
                () -> new PipelineNotFoundException(id)
            );

        return new PipelineResponse(
            pipeline.getId(),
            pipeline.getPipeName(),
            pipeline.getDescription()
        );
    }

    public List<PipelineResponse> getAll() {

        return pipelineRepository
            .findAll()
            .stream()
            .map(pipeline ->
                new PipelineResponse(
                    pipeline.getId(),
                    pipeline.getPipeName(),
                    pipeline.getDescription()
                )
            )
            .toList();
    }

    public PipelineResponse update(
        UUID id,
        String name,
        String description
    ) {

        Pipeline pipeline = pipelineRepository
            .findById(id)
            .orElseThrow(
                () -> new PipelineNotFoundException(id)
            );

        pipeline.update(name, description);

        Pipeline updatedPipeline =
            pipelineRepository.save(pipeline);

        return new PipelineResponse(
            updatedPipeline.getId(),
            updatedPipeline.getPipeName(),
            updatedPipeline.getDescription()
        );
    }

    public void delete(UUID id) {

        Pipeline pipeline = pipelineRepository
            .findById(id)
            .orElseThrow(
                () -> new PipelineNotFoundException(id)
            );

        pipelineRepository.delete(pipeline);
    }
}