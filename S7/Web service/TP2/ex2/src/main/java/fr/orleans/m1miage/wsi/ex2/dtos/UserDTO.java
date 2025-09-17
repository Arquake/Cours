package fr.orleans.m1miage.wsi.ex2.dtos;

import java.io.Serializable;
import java.util.UUID;

public class UserDTO implements Serializable {
    private String name;

    private String password;


    public UserDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
