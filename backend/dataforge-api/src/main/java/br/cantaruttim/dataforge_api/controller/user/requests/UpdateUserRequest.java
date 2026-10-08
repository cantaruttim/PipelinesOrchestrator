package br.cantaruttim.dataforge_api.controller.user.requests;

public record UpdateUserRequest(
    String userName,
    String userEmail
) {}
