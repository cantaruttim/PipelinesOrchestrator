package br.cantaruttim.dataforge_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.cantaruttim.dataforge_api.exceptions.pipelines.PipelineNotFoundException;
import br.cantaruttim.dataforge_api.models.pipelines.Pipeline;
import br.cantaruttim.dataforge_api.repositories.pipeline.PipelineRepository;

@Service 
public class PipelineService {
    
    private final PipelineRepository pipelineRepository;

    public PipelineService(PipelineRepository pipelineRepository) {
        this.pipelineRepository = pipelineRepository;
    }

    public Pipeline create(String name, String description) {
        Pipeline Pipeline = new Pipeline(
            UUID.randomUUID(),
            name,
            description
        );

        return pipelineRepository.save(Pipeline);
    }


    public Pipeline getById(UUID id) {
        
        return pipelineRepository
                    .findById(id)
                    .orElseThrow(
                        () -> new PipelineNotFoundException(id)
                    ); 
    }

    public List<Pipeline> getAll() {
        return pipelineRepository.findAll();
    }

    public Pipeline update(
        UUID id, String name, String description
    ) {
        Pipeline Pipeline = getById(id);

        Pipeline.update(name, description);

        return pipelineRepository.save(Pipeline);
    }

    public void delete(UUID id) {
        Pipeline pipeline = getById(id);
        pipelineRepository.delete(pipeline);
    }


}
