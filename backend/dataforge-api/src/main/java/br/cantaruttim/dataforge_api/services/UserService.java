package br.cantaruttim.dataforge_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.cantaruttim.dataforge_api.exceptions.UserNotFoundException;
import br.cantaruttim.dataforge_api.exceptions.RoleNotFoundException;
import br.cantaruttim.dataforge_api.models.roles.Role;
import br.cantaruttim.dataforge_api.models.users.User;
import br.cantaruttim.dataforge_api.models.users.UserAndRole;
import br.cantaruttim.dataforge_api.models.users.UserAndRoleResponse;
import br.cantaruttim.dataforge_api.repositories.RoleRepository;
import br.cantaruttim.dataforge_api.repositories.UserRepository;
import jakarta.transaction.Transactional;


@Service
public class UserService {

    // dependency ingestion
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(
        UserRepository userRepository,
        RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    @Transactional 
    public UserAndRoleResponse addRole(UUID userId, UUID roleId) {

        User user = userRepository
            .findById(userId)
            .orElseThrow(
                () -> new UserNotFoundException("User not found!")
            );
        
        Role role = roleRepository
            .findById(roleId)
            .orElseThrow(
                () -> new RoleNotFoundException(roleId)
            );

            user.addRole(role);
            
            return new UserAndRoleResponse(
                user.getId(),
                user.getUserName(),
                role.getId(),
                role.getDescription()
            );
        
    }

}
