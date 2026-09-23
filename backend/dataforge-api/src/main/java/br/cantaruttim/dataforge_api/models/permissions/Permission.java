package br.cantaruttim.dataforge_api.models.permissions;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.cantaruttim.dataforge_api.models.roles.RoleAndPermissions;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    private UUID id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "permission")
    private List<RoleAndPermissions> roleAndPermissions = new ArrayList<>();

    protected Permission() {
    }

    public Permission(
            UUID id,
            String name,
            String description
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<RoleAndPermissions> getRoleAndPermissions() {
        return roleAndPermissions;
    }
}