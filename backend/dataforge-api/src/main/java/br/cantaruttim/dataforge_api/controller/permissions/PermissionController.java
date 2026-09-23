package br.cantaruttim.dataforge_api.controller.permissions;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.cantaruttim.dataforge_api.models.permissions.Permission;
import br.cantaruttim.dataforge_api.services.PermissionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public ResponseEntity<Permission> create(
            @Valid @RequestBody CreatePermissionRequest request
    ) {

        Permission permission = permissionService.create(
            request.name(),
            request.description()
        );

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(permission);
    }

    @GetMapping
    public ResponseEntity<List<Permission>> getAll() {

        return ResponseEntity.ok(
            permissionService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permission> getById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
            permissionService.getById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permission> update(
            @PathVariable UUID id,
            @RequestBody UpdatePermissionRequest request
    ) {

        return ResponseEntity.ok(
            permissionService.update(
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

        permissionService.delete(id);

        return ResponseEntity.noContent().build();
    }
}