package com.example.minimarketplace.model.user;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-02
 *
 *  User controller uses this to receive login info instead of entire user obj
 */
public class LoginRequest {
    private String username;
    private String password;

    // getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
