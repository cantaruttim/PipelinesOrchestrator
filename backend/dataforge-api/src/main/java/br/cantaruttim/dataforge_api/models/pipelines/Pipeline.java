package br.cantaruttim.dataforge_api.models.pipelines;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.cantaruttim.dataforge_api.models.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.persistence.CascadeType;

@Entity 
@Table(name = "pipelines")
public class Pipeline {
    
    @Id 
    private UUID id;
    
    private String pipeName;
    private String description;

    @OneToMany(
        mappedBy = "pipeline",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<PipelineAndUser> pipelineUsers = new ArrayList<>();



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

    public void update(
        String name, String description
    ) {
        this.pipeName = name;
        this.description = description;
    }

    public List<PipelineAndUser> getPipelineUsers() {
        return pipelineUsers;
    }

    public void addUser(User user) {
        PipelineAndUser pipelineUser = new PipelineAndUser(
            UUID.randomUUID(),
            this,
            user
        );

        pipelineUsers.add(pipelineUser);
    }

}
