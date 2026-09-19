package br.cantaruttim.dataforge_api.models.users;

import java.util.UUID;

public class User {
    
    private UUID id;
    private String userName;
    private String userEmail;
    private boolean activate;

    public User(
        UUID id, 
        String userName, 
        String userEmail
    ) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.activate = true;
    }

    public User() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public void deactivateUser() {
        this.activate = false;
    }
    
    
}
