package br.cantaruttim.dataforge_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.cantaruttim.dataforge_api.exceptions.pipelines.PipelineNotFoundException;
import br.cantaruttim.dataforge_api.exceptions.users.UserAndPipelineAlredyExistsException;
import br.cantaruttim.dataforge_api.exceptions.users.UserNotFoundException;
import br.cantaruttim.dataforge_api.models.pipelines.Pipeline;
import br.cantaruttim.dataforge_api.models.pipelines.records.PipelineResponse;
import br.cantaruttim.dataforge_api.models.users.User;
import br.cantaruttim.dataforge_api.models.users.records.UserAndPipelineResponse;
import br.cantaruttim.dataforge_api.repositories.pipeline.PipelineAndUserRepository;
import br.cantaruttim.dataforge_api.repositories.pipeline.PipelineRepository;
import br.cantaruttim.dataforge_api.repositories.user.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class PipelineService {

    private final PipelineRepository pipelineRepository;
    private final PipelineAndUserRepository pipelineAndUserRepository;
    private final UserRepository userRepository;

    
    public PipelineService(
        PipelineRepository pipelineRepository, 
        PipelineAndUserRepository pipelineAndUserRepository,
            UserRepository userRepository
    ) {
        this.pipelineRepository = pipelineRepository;
        this.pipelineAndUserRepository = pipelineAndUserRepository;
        this.userRepository = userRepository;
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

    @Transactional  
    public UserAndPipelineResponse addUser(UUID pipelineId, UUID userId) {

        Pipeline pipeline = pipelineRepository
                .findById(pipelineId)
                .orElseThrow(
                    () -> new PipelineNotFoundException(pipelineId)
                );

        User user = userRepository
            .findById(userId)
            .orElseThrow(
                () -> new UserNotFoundException("User not found!")
            );
        

        // valida se a chave já existe, se existe nem chega no banco
        if (
            pipelineAndUserRepository.existsByPipelineIdAndUserId(
                pipelineId,
                userId
        )) {
            throw new UserAndPipelineAlredyExistsException(
                pipelineId,
                userId
            );
        }

        user.addPipeline(pipeline);
            
        return new UserAndPipelineResponse(
            pipeline.getId(),
            pipeline.getPipeName(),
            user.getId(),
            user.getUserEmail()
        );
    }

}