package br.cantaruttim.dataforge_api.models.users;

import java.util.UUID;

import br.cantaruttim.dataforge_api.models.roles.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "user_roles")
public class UserAndRole {
    
    @Id 
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    protected UserAndRole() {}

    public UserAndRole(UUID id, User user, Role role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
    
}
