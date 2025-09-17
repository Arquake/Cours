package fr.orleans.m1miage.wsi.ex2.dtos;

import java.io.Serializable;

public class VideoDTO implements Serializable {

    private String titre;

    private String description;

    private String url;

    public VideoDTO() {
    }

    public VideoDTO(String titre, String description, String url) {
        this.titre = titre;
        this.description = description;
        this.url = url;
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
}
