package br.cantaruttim.dataforge_api.models.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "role_permissions")
public class RoleAndPermissions {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false) 
    private Role role;

    @ManyToOne 
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    protected RoleAndPermissions() {}

    public RoleAndPermissions(UUID id, Role role, Permission permission) {
        this.id = id;
        this.role = role;
        this.permission = permission;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    
}