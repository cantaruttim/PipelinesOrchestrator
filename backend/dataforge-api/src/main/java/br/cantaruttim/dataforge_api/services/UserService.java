package br.cantaruttim.dataforge_api.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.cantaruttim.dataforge_api.models.users.User;


@Service
public class UserService {

    public User createUser(String name, String email) {

        UUID id = UUID.randomUUID();

        return new User(
                id,
                name,
                email
        );
    }
}
