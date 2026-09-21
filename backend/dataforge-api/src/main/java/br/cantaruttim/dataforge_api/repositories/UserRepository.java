package br.cantaruttim.dataforge_api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cantaruttim.dataforge_api.models.users.User;

public 
    interface UserRepository 
    extends JpaRepository<User, UUID> {}
