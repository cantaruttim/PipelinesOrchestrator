package br.cantaruttim.dataforge_api;

import java.util.UUID;

import br.cantaruttim.dataforge_api.models.permissions.Permission;
import br.cantaruttim.dataforge_api.models.roles.Role;
import br.cantaruttim.dataforge_api.models.users.User;

public class RbacTest {

    public static void main(String[] args) {

        Permission pipelineExecute = new Permission(
            UUID.randomUUID(),
            "pipeline:execute",
            "Permite executar pipelines"
        );

        Role dataEngineer = new Role(
            UUID.randomUUID(),
            "DATA_ENGINEER",
            "Permissões para execução e desenvolvimento de pipelines"
        );

        User user = new User(
            UUID.randomUUID(),
            "Matheus",
            "matheus@email.com"
        );

        dataEngineer.addPermission(pipelineExecute);
        user.addRole(dataEngineer);

        System.out.println("Usuário: " + user.getUserName());
        System.out.println("Role: " + user.getUserRoles().get(0).getRole().getName());
        System.out.println(
            "Permission: " +
            dataEngineer
                .getRoleAndPermissions()
                .get(0)
                .getPermission()
                .getName()
        );
    }
}