package fr.orleans.m1miage.wsi.ex2.dtos;

import java.io.Serializable;

public class PlaylistCreationDTO implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
