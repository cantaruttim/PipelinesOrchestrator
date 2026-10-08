package br.cantaruttim.dataforge_api.models.pipelines;

import java.util.UUID;

import br.cantaruttim.dataforge_api.models.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "pipeline_user")
public class PipelineAndUser {
    
    @Id 
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pipeline_id", nullable = false)
    private Pipeline pipeline;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected PipelineAndUser() {}

    public PipelineAndUser(UUID id, Pipeline pipeline, User user) {
        this.id = id;
        this.pipeline = pipeline;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
