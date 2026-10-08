package br.cantaruttim.dataforge_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.cantaruttim.dataforge_api.exceptions.roles.RoleNotFoundException;
import br.cantaruttim.dataforge_api.exceptions.users.UserNotFoundException;
import br.cantaruttim.dataforge_api.exceptions.users.UserRoleAlreadyExistsException;
import br.cantaruttim.dataforge_api.exceptions.users.UserRoleNotFoundException;
import br.cantaruttim.dataforge_api.models.roles.Role;
import br.cantaruttim.dataforge_api.models.roles.records.RoleResponse;
import br.cantaruttim.dataforge_api.models.users.User;
import br.cantaruttim.dataforge_api.models.users.UserAndRole;
import br.cantaruttim.dataforge_api.models.users.records.UserAndRoleResponse;
import br.cantaruttim.dataforge_api.repositories.role.RoleRepository;
import br.cantaruttim.dataforge_api.repositories.user.UserAndRolesRepository;
import br.cantaruttim.dataforge_api.repositories.user.UserRepository;
import jakarta.transaction.Transactional;


@Service
public class UserService {

    // dependency ingestion
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserAndRolesRepository userAndRoleRepository;

    public UserService(
        UserRepository userRepository,
        RoleRepository roleRepository,
        UserAndRolesRepository userAndRolesRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userAndRoleRepository = userAndRolesRepository;
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

        // valida se a chave já existe, se existe nem chega no banco
        if (
            userAndRoleRepository.existsByUserIdAndRoleId(
                userId,
                roleId
        )) {
            throw new UserRoleAlreadyExistsException(
                userId,
                roleId
            );
        }

        user.addRole(role);
            
        return new UserAndRoleResponse(
            user.getId(),
            user.getUserName(),
            role.getId(),
            role.getDescription()
        );
        
    }

    public UserAndRoleResponse getUserRoleById(UUID id) {

        UserAndRole userAndRole = 
            userAndRoleRepository
                .findById(id)
                .orElseThrow(
                    () -> new UserRoleNotFoundException(id)
                );
            

        return new UserAndRoleResponse(
            userAndRole.getUser().getId(),
            userAndRole.getUser().getUserName(),
            userAndRole.getRole().getId(),
            userAndRole.getRole().getName()
        );
    }

    public List<UserAndRoleResponse> getAllUserRole() {
        return userAndRoleRepository
                    .findAll()
                    .stream()
                    .map(userAndRole ->
                            new UserAndRoleResponse(
                                userAndRole.getUser().getId(),
                                userAndRole.getUser().getUserName(),
                                userAndRole.getRole().getId(),
                                userAndRole.getRole().getName()
                            )
                    )
                    .toList();
    }

    public List<RoleResponse> getRoleByUser(UUID userId) {
      
        getUserById(userId);

        return userAndRoleRepository
                .findByUserId(userId)
                .stream()
                .map(
                    userAndRole ->
                    new RoleResponse(
                        userAndRole.getRole().getId(),
                        userAndRole.getRole().getName(),
                        userAndRole.getRole().getDescription()
                    )
                )
                .toList();

    }

}
