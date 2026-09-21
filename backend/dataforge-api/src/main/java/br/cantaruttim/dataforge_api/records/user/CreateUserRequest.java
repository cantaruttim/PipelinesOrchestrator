package br.cantaruttim.dataforge_api.records.user;

public record CreateUserRequest(
    String name,
    String email
) {}
