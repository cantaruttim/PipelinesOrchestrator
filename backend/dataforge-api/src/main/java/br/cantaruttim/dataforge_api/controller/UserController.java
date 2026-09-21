package br.cantaruttim.dataforge_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cantaruttim.dataforge_api.models.users.User;
import br.cantaruttim.dataforge_api.records.user.CreateUserRequest;
import br.cantaruttim.dataforge_api.records.user.UpdateUserRequest;
import br.cantaruttim.dataforge_api.services.UserService;

@RestController 
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping 
    public User createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(
            request.name(), request.email()
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
                request.name(),
                request.email()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
    
}
