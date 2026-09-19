package br.cantaruttim.dataforge_api.models.users;

import java.util.UUID;

public class UserDemo {

    public static void main(String[] args) {

        User user = new User(
                UUID.randomUUID(),
                "Matheus",
                "matheus@example.com"
        );

        System.out.println(user.getUserName());
        System.out.println(user.getUserEmail());
        System.out.println(user.isActivate());

        System.out.println("Usuário desativado");
        user.deactivateUser();
        System.out.println("Usuário ativo: " + user.isActivate());
    }
}