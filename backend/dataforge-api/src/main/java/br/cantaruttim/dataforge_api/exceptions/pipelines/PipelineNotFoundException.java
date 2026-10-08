package br.cantaruttim.dataforge_api.exceptions.pipelines;

import java.util.UUID;

public class PipelineNotFoundException extends RuntimeException {
    
    public PipelineNotFoundException(UUID id) {
        super("Pipeline not found: " + id);
    }

}
