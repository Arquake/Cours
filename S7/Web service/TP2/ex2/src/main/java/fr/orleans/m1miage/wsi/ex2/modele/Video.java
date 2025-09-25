package fr.orleans.m1miage.wsi.ex2.modele;

import java.util.UUID;

public class Video {

    public UUID getUuid() {
        return uuid;
    }

    private UUID uuid;

    private String url;

    private String decription;

    private String titre;

    public UUID getUserUUID() {
        return userUUID;
    }

    private UUID userUUID;

    public Video() {
        this.uuid = UUID.randomUUID();
    }

    public Video(String url, String decription, String titre, UUID userUUID) {
        this.uuid = UUID.randomUUID();
        this.url = url;
        this.decription = decription;
        this.titre = titre;
        this.userUUID = userUUID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setUserUUID(UUID userUUID) {
        this.userUUID = userUUID;
    }
}
