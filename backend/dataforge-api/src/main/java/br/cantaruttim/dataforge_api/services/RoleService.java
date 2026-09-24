package br.cantaruttim.dataforge_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.cantaruttim.dataforge_api.exceptions.PermissionNotFoundException;
import br.cantaruttim.dataforge_api.exceptions.RoleNotFoundException;
import br.cantaruttim.dataforge_api.models.permissions.Permission;
import br.cantaruttim.dataforge_api.models.roles.Role;
import br.cantaruttim.dataforge_api.repositories.PermissionRepository;
import br.cantaruttim.dataforge_api.repositories.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleService(
            RoleRepository roleRepository,
            PermissionRepository permissionRepository
    ) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public Role create(
            String name,
            String description
    ) {

        Role role = new Role(
            UUID.randomUUID(),
            name,
            description
        );

        return roleRepository.save(role);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getById(UUID id) {

        return roleRepository
            .findById(id)
            .orElseThrow(() -> new RoleNotFoundException(id));
    }

    public Role update(
            UUID id,
            String name,
            String description
    ) {

        Role role = getById(id);

        role.update(
            name,
            description
        );

        return roleRepository.save(role);
    }

    public void delete(UUID id) {

        Role role = getById(id);

        roleRepository.delete(role);
    }

    // Caso de Associação entre Roles e Permissions
    public Role addPermission(
        UUID roleId,
        UUID permissionId
    ) {

        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException(roleId));

        Permission permission = permissionRepository.findById(permissionId).orElseThrow(() -> new PermissionNotFoundException(permissionId));

        role.addPermission(permission);
        return roleRepository.save(role);
    }

}