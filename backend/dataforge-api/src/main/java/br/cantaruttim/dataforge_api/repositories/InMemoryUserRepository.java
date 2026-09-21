package br.cantaruttim.dataforge_api.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.cantaruttim.dataforge_api.models.users.User;

@Repository 
public class InMemoryUserRepository implements UserRepository {
    
    private final List<User> users = new ArrayList<>();

    @Override 
    public User save(User user) {
        users.add(user);
        return user;
    }

}
