package br.cantaruttim.dataforge_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.cantaruttim.dataforge_api.exceptions.PermissionNotFoundException;
import br.cantaruttim.dataforge_api.models.permissions.Permission;
import br.cantaruttim.dataforge_api.repositories.PermissionRepository;

@Service 
public class PermissionService {
    
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission getById(UUID id) {

    return permissionRepository
        .findById(id)
        .orElseThrow(() -> new PermissionNotFoundException(id));
    }

    public Permission create(String name, String description) {
        Permission permission = new Permission(
            UUID.randomUUID(),
            name,
            description
        );

        // o Spring JPA utiliza o Hibernate para transformar essa entidade
        // em uma operação SQL
        return permissionRepository.save(permission);
    }

    public List<Permission> getAll() {
        return permissionRepository.findAll();
    }

    public Permission update(
        UUID id, String name, String description
    ) {
        Permission permission = getById(id);

        permission.update(name, description);

        return permissionRepository.save(permission);
    }

    public void delete(UUID id) {
        Permission permission = getById(id);
        permissionRepository.delete(permission);
    }
    
}
