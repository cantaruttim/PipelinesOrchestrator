package br.cantaruttim.dataforge_api.controller.user;

public record UpdateUserRequest(
    String userName,
    String userEmail
) {}
