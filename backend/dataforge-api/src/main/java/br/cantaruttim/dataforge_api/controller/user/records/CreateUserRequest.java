package br.cantaruttim.dataforge_api.controller.user.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(

    @NotBlank 
    String userName,

    @NotBlank 
    @Email 
    String userEmail
) {}
