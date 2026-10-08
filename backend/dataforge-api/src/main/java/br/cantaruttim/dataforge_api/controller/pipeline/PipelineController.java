package br.cantaruttim.dataforge_api.controller.pipeline;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cantaruttim.dataforge_api.controller.pipeline.requests.CreatePipelineRequest;
import br.cantaruttim.dataforge_api.controller.pipeline.requests.UpdatePipelineRequest;
import br.cantaruttim.dataforge_api.models.pipelines.Pipeline;
import br.cantaruttim.dataforge_api.services.PipelineService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/pipelines")
public class PipelineController {

    private final PipelineService pipelineService;

    public PipelineController(PipelineService pipelineService) {
        this.pipelineService = pipelineService;
    }

    @PostMapping
    public ResponseEntity<Pipeline> create(
            @Valid @RequestBody CreatePipelineRequest request
    ) {

        Pipeline pipeline = pipelineService.create(
            request.name(),
            request.description()
        );

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(pipeline);
    }

    @GetMapping
    public ResponseEntity<List<Pipeline>> getAll() {

        return ResponseEntity.ok(
            pipelineService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pipeline> getById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
            pipelineService.getById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pipeline> update(
            @PathVariable UUID id,
            @RequestBody UpdatePipelineRequest request
    ) {

        return ResponseEntity.ok(
            pipelineService.update(
                id,
                request.name(),
                request.description()
            )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        pipelineService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
