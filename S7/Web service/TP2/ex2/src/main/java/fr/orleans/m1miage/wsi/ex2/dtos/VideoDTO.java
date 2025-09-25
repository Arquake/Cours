package fr.orleans.m1miage.wsi.ex2.dtos;

import java.io.Serializable;

public class VideoDTO implements Serializable {

    private String titre;

    private String description;

    private String url;

    private String id;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public VideoDTO(String titre, String description, String url, String id, String userId) {
        this.titre = titre;
        this.description = description;
        this.url = url;
        this.id = id;
        this.userId = userId;
    }

    public VideoDTO() {
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
