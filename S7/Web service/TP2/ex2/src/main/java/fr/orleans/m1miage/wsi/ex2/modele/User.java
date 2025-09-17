package fr.orleans.m1miage.wsi.ex2.modele;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class User {

    private String nom;
    private String password;
    private UUID id;

    private final Map<UUID, List<Video>> playlists;

    User() {
        this.playlists = new HashMap<>();
    }

    public User(String nom, String password) {
        this.nom = nom;
        this.password = password;
        this.id = UUID.randomUUID();
        this.playlists = new HashMap<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }
}
