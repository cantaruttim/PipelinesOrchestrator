package br.cantaruttim.dataforge_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.cantaruttim.dataforge_api.exceptions.PermissionNotFoundException;
import br.cantaruttim.dataforge_api.exceptions.RoleNotFoundException;
import br.cantaruttim.dataforge_api.exceptions.RolePermissionAlreadyExistsException;
import br.cantaruttim.dataforge_api.exceptions.RolePermissionNotFoundException;
import br.cantaruttim.dataforge_api.models.permissions.Permission;
import br.cantaruttim.dataforge_api.models.permissions.records.PermissionResponse;
import br.cantaruttim.dataforge_api.models.roles.Role;
import br.cantaruttim.dataforge_api.models.roles.RoleAndPermissions;
import br.cantaruttim.dataforge_api.models.roles.records.RoleAndPermissionResponse;
import br.cantaruttim.dataforge_api.models.roles.records.RoleResponse;
import br.cantaruttim.dataforge_api.repositories.permissions.PermissionRepository;
import br.cantaruttim.dataforge_api.repositories.role.RoleAndPermissionsRepository;
import br.cantaruttim.dataforge_api.repositories.role.RoleRepository;
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

    /* 
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
    */
    public RoleResponse create(
        String name,
        String description
    ) {
        Role role = new Role(
            UUID.randomUUID(),
            name,
            description
        );

        Role savedRole = roleRepository.save(role);

        return new RoleResponse(
            savedRole.getId(),
            savedRole.getName(),
            savedRole.getDescription()
        );
    }


    /*
        public List<Role> getAll() {
            return roleRepository.findAll();
        }    
    */

    public List<RoleResponse> getAll() {
    return roleRepository
        .findAll()
        .stream()
        .map(role ->
            new RoleResponse(
                role.getId(),
                role.getName(),
                role.getDescription()
            )
        )
        .toList();
}

    /*
        public Role getById(UUID id) {
            return roleRepository
                .findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
        }    
    */
    public RoleResponse getById(UUID id) {
        Role role = findRoleById(id);

        return new RoleResponse(
            role.getId(),
            role.getName(),
            role.getDescription()
        );
    }

    private Role findRoleById(UUID id) {
        return roleRepository
            .findById(id)
            .orElseThrow(() -> new RoleNotFoundException(id));
    }

    /*
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
    */
    public RoleResponse update(
        UUID id,
        String name,
        String description
    ) {
        Role role = findRoleById(id);

        role.update(name, description);

        Role updatedRole = roleRepository.save(role);

        return new RoleResponse(
            updatedRole.getId(),
            updatedRole.getName(),
            updatedRole.getDescription()
        );
    }

    /* 
        public void delete(UUID id) {
            Role role = getById(id);
            roleRepository.delete(role);
        }
    */
    public void delete(UUID id) {

    Role role = findRoleById(id);

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

        // valida se a chave já existe, se existe nem chega no banco
        if (roleAndPermissionsRepository.existsByRoleIdAndPermissionId(
            roleId,
            permissionId
        )) {
            throw new RolePermissionAlreadyExistsException(
            roleId,
            permissionId
            );
        }
        
        // persiste o dado no banco
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
                .orElseThrow(
                    () -> new RolePermissionNotFoundException(id)
                );

        return new RoleAndPermissionResponse(
            roleAndPermission.getRole().getId(),
            roleAndPermission.getRole().getName(),
            roleAndPermission.getPermission().getId(),
            roleAndPermission.getPermission().getName()
        );
    }

    public List<RoleAndPermissionResponse> getAllRolePermissions() {
        return roleAndPermissionsRepository
                    .findAll()
                    .stream()
                    .map(roleAndPermission ->
                        new RoleAndPermissionResponse(
                            roleAndPermission.getRole().getId(),
                            roleAndPermission.getRole().getName(),
                            roleAndPermission.getPermission().getId(),
                            roleAndPermission.getPermission().getName()
                        )
                    )
                    .toList();
    }

    public List<PermissionResponse> getPermissionsByRole(UUID roleId) {

        // verifica primeiro se a role existe
        getById(roleId);
        
        // depois, retornamos as permissões
        return roleAndPermissionsRepository
                    .findByRoleId(roleId)
                    .stream()
                    .map(roleAndPermission ->
                        new PermissionResponse(
                            roleAndPermission.getPermission().getId(),
                            roleAndPermission.getPermission().getName()
                        )
                    )
                    .toList();
    }

}