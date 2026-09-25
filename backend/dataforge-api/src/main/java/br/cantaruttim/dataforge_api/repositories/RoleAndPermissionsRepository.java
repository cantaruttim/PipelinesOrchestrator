package br.cantaruttim.dataforge_api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cantaruttim.dataforge_api.models.roles.RoleAndPermissions;

public interface RoleAndPermissionsRepository 
    extends JpaRepository<RoleAndPermissions, UUID> {

        boolean existsByRoleIdAndPermissionId(
            UUID roleId,
            UUID permissionId
        );

        /*
            Equivalente à:

            SELECT EXISTS (
                SELECT 
                    1
                FROM role_permissions
                WHERE role_id = ? AND permission_id = ?
            );
        */

        List<RoleAndPermissions> findByRoleId(UUID roleId);
        /*

            Equivalente a 

            SELECT 
                *
            FROM role_permissions
            WHERE role_id = ?;
        
        */
}
