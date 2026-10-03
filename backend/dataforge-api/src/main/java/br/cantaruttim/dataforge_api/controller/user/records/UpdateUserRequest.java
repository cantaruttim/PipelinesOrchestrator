package br.cantaruttim.dataforge_api.controller.user.records;

public record UpdateUserRequest(
    String userName,
    String userEmail
) {}
