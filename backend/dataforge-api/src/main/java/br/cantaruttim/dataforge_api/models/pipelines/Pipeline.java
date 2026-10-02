package br.cantaruttim.dataforge_api.models.pipelines;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "pipelines")
public class Pipeline {
    
    @Id 
    private UUID id;
    
    private String pipeName;
    private String description;

    protected Pipeline () {}

    public Pipeline(UUID id, String pipeName, String description) {
        this.id = id;
        this.pipeName = pipeName;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPipeName() {
        return pipeName;
    }

    public void setPipeName(String pipeName) {
        this.pipeName = pipeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

}
