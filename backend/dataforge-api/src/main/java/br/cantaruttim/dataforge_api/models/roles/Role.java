package br.cantaruttim.dataforge_api.models.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.cantaruttim.dataforge_api.models.permissions.Permission;
import br.cantaruttim.dataforge_api.models.users.UserAndRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name = "roles")
public class Role {
    @Id 
    private UUID id;
    private String name;
    private String description;

    @OneToMany(
        mappedBy = "role", // a relação é controlada pelo atributo Role que existe dentro de RoleAndPermissions
        cascade = CascadeType.ALL, // certas operações feitas sobre o Role podem ser propagadas para os RoleAndPermissions
        orphanRemoval = true // associações removidas da coleção podem ser removidas do banco
    )
    private List<RoleAndPermissions> roleAndPermissions = new ArrayList<>();


    @OneToMany(
        mappedBy = "role",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<UserAndRole> userRoles = new ArrayList<>();
    
    protected Role() {}

    public Role(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RoleAndPermissions> getRoleAndPermissions() {
        return roleAndPermissions;
    }

    public List<UserAndRole> getUserRoles() {
        return userRoles;
    }  

    public void addPermission(Permission permission) {

        RoleAndPermissions roleAndPermission = 
        new 
            RoleAndPermissions(
                    UUID.randomUUID(),
                    this,
                    permission
            );

        roleAndPermissions.add(roleAndPermission);
    }



    

}
