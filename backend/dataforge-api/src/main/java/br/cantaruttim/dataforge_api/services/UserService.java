package br.cantaruttim.dataforge_api.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.cantaruttim.dataforge_api.models.users.User;
import br.cantaruttim.dataforge_api.repositories.UserRepository;


@Service
public class UserService {

    // dependency ingestion
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, String email) {

        UUID id = UUID.randomUUID();
        User user = new User(id, name, email);

        return userRepository.save(user);
    }
}
