package br.cantaruttim.dataforge_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.cantaruttim.dataforge_api.exceptions.UserNotFoundException;
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository
                    .findById(id)
                    .orElseThrow(
                        () -> new 
                            UserNotFoundException("User " + id + " not found!")
                    );
    }

    public User updateUser(UUID id, String name, String email) {
        User user = userRepository.findById(id).orElseThrow();
        user.update(name, email);
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        User user = userRepository
                        .findById(id)
                        .orElseThrow(
                            () -> new 
                            UserNotFoundException("User " + id + " not found! User not deleted!")
                        );                        
        user.deactivateUser();
        userRepository.save(user);
    }

}
