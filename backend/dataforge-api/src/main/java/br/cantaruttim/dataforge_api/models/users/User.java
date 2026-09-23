package br.cantaruttim.dataforge_api.models.users;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.cantaruttim.dataforge_api.models.roles.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // entidade que será persistida no banco
@Table(name = "users")
public class User {
    
    @Id // atributo chave para essa entidade.
    private UUID id;
    private String userName;
    private String userEmail;
    private boolean activate;

    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<UserAndRole> userRoles = new ArrayList<>();

    public User(
        UUID id, 
        String userName, 
        String userEmail
    ) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.activate = true;
    }

    protected User() {}

    // public User() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public void deactivateUser() {
        this.activate = false;
    }

    public List<UserAndRole> getUserRoles() {
        return userRoles;
    }

    
    public void update(String name, String email) {
        this.userName = name;
        this.userEmail = email;
    }

    public void addRole(Role role) {
        
        UserAndRole userRole = new UserAndRole(
            UUID.randomUUID(),
            this,
            role
        );

        userRoles.add(userRole);
    }


    
}
