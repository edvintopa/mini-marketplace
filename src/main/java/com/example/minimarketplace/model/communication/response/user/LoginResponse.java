package com.example.minimarketplace.model.communication.response.user;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-12
 */
public class LoginResponse {
    private String username;
    private String token;

    public LoginResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
