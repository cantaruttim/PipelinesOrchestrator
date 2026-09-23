package br.cantaruttim.dataforge_api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import br.cantaruttim.dataforge_api.models.permissions.Permission;
import br.cantaruttim.dataforge_api.models.roles.Role;
import br.cantaruttim.dataforge_api.models.users.User;

@SpringBootTest
@Transactional
class RbacPersistenceTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldPersistUserRoleAndPermission() {

        Permission permission = new Permission(
            UUID.randomUUID(),
            "pipeline:execute",
            "Permite executar pipelines"
        );

        Role role = new Role(
            UUID.randomUUID(),
            "DATA_ENGINEER",
            "Permissões para engenharia de dados"
        );

        User user = new User(
            UUID.randomUUID(),
            "Matheus",
            "matheus@email.com"
        );

        role.addPermission(permission);
        user.addRole(role);

        entityManager.persist(permission);
        entityManager.persist(role);
        entityManager.persist(user);

        entityManager.flush();

        assertEquals(1, user.getUserRoles().size());
        assertEquals(1, role.getRoleAndPermissions().size());
    }
}