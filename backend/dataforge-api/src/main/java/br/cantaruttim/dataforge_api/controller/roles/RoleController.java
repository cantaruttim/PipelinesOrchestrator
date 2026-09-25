package br.cantaruttim.dataforge_api.controller.roles;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.cantaruttim.dataforge_api.models.roles.Role;
import br.cantaruttim.dataforge_api.models.roles.records.RoleAndPermissionResponse;
import br.cantaruttim.dataforge_api.services.RoleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> create(
            @Valid @RequestBody CreateRoleRequest request
    ) {

        Role role = roleService.create(
            request.name(),
            request.description()
        );

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(role);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAll() {

        return ResponseEntity.ok(
            roleService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
            roleService.getById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> update(
            @PathVariable UUID id,
            @RequestBody UpdateRoleRequest request
    ) {

        return ResponseEntity.ok(
            roleService.update(
                id,
                request.name(),
                request.description()
            )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        roleService.delete(id);

        return ResponseEntity
            .noContent()
            .build();
    }

    /*
        responsável por criar a rota que cria a associacao 
        RoleAndPermission, por meio do Servico
        addPermission
    */ 
    @PostMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<RoleAndPermissionResponse> addPermission(
            @PathVariable UUID roleId,
            @PathVariable UUID permissionId
    ) {
        return ResponseEntity.ok(
            roleService.addPermission(
                roleId,
                permissionId
            )
        );
    }

    @GetMapping("/role-permissions/{id}")
    public ResponseEntity<RoleAndPermissionResponse> getRolePermissionById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
            roleService.getRolePermissionById(id)
        );
    }

    @GetMapping("/role-permissions")
    // todas as associações de uma unica associação de Permissions
    public ResponseEntity<List<RoleAndPermissionResponse>> getAllRolePermissions() {
        return ResponseEntity
                .ok(
                    roleService.getAllRolePermissions()
                );
    }


}