package com.example.tp4.dtos;

public class UserDTO {

    String username;
    String[] roles;


    public UserDTO() {
    }
    public UserDTO(String username, String[] roles) {
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
