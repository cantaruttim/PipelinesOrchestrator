package br.cantaruttim.dataforge_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.cantaruttim.dataforge_api.exceptions.PermissionNotFoundException;
import br.cantaruttim.dataforge_api.exceptions.RoleNotFoundException;
import br.cantaruttim.dataforge_api.models.permissions.Permission;
import br.cantaruttim.dataforge_api.models.roles.Role;
import br.cantaruttim.dataforge_api.models.roles.RoleAndPermissions;
import br.cantaruttim.dataforge_api.models.roles.records.RoleAndPermissionResponse;
import br.cantaruttim.dataforge_api.repositories.PermissionRepository;
import br.cantaruttim.dataforge_api.repositories.RoleAndPermissionsRepository;
import br.cantaruttim.dataforge_api.repositories.RoleRepository;

import jakarta.transaction.Transactional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleAndPermissionsRepository roleAndPermissionsRepository;

    public RoleService(
            RoleRepository roleRepository,
            PermissionRepository permissionRepository,
            RoleAndPermissionsRepository roleAndPermissionsRepository
    ) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.roleAndPermissionsRepository = roleAndPermissionsRepository;
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
    @Transactional
    public RoleAndPermissionResponse addPermission(
            UUID roleId,
            UUID permissionId
    ) {
        Role role = roleRepository
            .findById(roleId)
            .orElseThrow(
                () -> new RoleNotFoundException(roleId)
            );

        Permission permission = permissionRepository
            .findById(permissionId)
            .orElseThrow(
                () -> new PermissionNotFoundException(permissionId)
            );

        role.addPermission(permission);

        return new RoleAndPermissionResponse(
            role.getId(),
            role.getName(),
            permission.getId(),
            permission.getName()
        );
    }

    public RoleAndPermissionResponse getRolePermissionById(UUID id) {

        RoleAndPermissions roleAndPermission =
            roleAndPermissionsRepository
                .findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Role permission not found: " + id)
                );

        return new RoleAndPermissionResponse(
            roleAndPermission.getRole().getId(),
            roleAndPermission.getRole().getName(),
            roleAndPermission.getPermission().getId(),
            roleAndPermission.getPermission().getName()
        );
    }

}