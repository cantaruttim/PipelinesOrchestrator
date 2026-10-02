package br.cantaruttim.dataforge_api.controller.user;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cantaruttim.dataforge_api.models.roles.records.RoleResponse;
import br.cantaruttim.dataforge_api.models.users.User;
import br.cantaruttim.dataforge_api.models.users.UserAndRoleResponse;
import br.cantaruttim.dataforge_api.services.UserService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping 
    public User createUser(
        @Valid
        @RequestBody CreateUserRequest request
    ) {
        return userService.createUser(
            request.userName(), request.userEmail()
        );
    }

    @GetMapping 
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable UUID id,
            @RequestBody UpdateUserRequest request
    ) {

        return userService.updateUser(
                id,
                request.userName(),
                request.userEmail()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<UserAndRoleResponse> addRole(
        @PathVariable UUID userId,
        @PathVariable UUID roleId
    ) {
        return ResponseEntity.ok(
            userService.addRole(userId, roleId)
        );
    }

    @GetMapping("/user-roles/{id}")
    public ResponseEntity<UserAndRoleResponse> getUserRoleById(
        @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
            userService.getUserRoleById(id)
        );
    }

    @GetMapping("/{userId}/roles")
    public ResponseEntity<List<RoleResponse>> getRoleByUser(
        @PathVariable UUID userId
    ) {
        return ResponseEntity.ok(
            userService.getRoleByUser(userId)
        );
    }

    @GetMapping("/user-roles")
    public ResponseEntity<List<UserAndRoleResponse>> getAllUserRole() {
        return ResponseEntity.ok(
            userService.getAllUserRole()
        );
    }
    
}
