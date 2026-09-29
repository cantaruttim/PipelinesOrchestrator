package br.cantaruttim.dataforge_api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cantaruttim.dataforge_api.models.users.UserAndRole;

public interface UserAndRolesRepository 
    extends JpaRepository<UserAndRole, UUID> {

    boolean existsByUserIdAndRoleId(
        UUID userId,
        UUID roleId
    );

    List<UserAndRole> findByRoleId(UUID roleId);

}
