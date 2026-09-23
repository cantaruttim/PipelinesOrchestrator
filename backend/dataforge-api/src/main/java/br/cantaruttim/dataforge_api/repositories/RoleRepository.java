package br.cantaruttim.dataforge_api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cantaruttim.dataforge_api.models.roles.Role;

public 
    interface RoleRepository 
        extends JpaRepository<Role, UUID> {}
