package br.cantaruttim.dataforge_api.records.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(

    @NotBlank 
    String userName,

    @NotBlank 
    @Email 
    String userEmail
) {}
