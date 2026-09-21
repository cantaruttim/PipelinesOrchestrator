package br.cantaruttim.dataforge_api.records.user;

public record UpdateUserRequest(
    String userName,
    String userEmail
) {}
