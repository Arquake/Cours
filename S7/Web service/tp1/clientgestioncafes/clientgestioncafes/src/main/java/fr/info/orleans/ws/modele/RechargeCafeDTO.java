package fr.info.orleans.ws.modele;

import java.io.Serializable;

public class RechargeCafeDTO implements Serializable {
    private String dateRecharge;
    private int nbKilos;
    private UtilisateurDTO utilisateurDTO;
    private String id;


    public String getDateRecharge() {
        return dateRecharge;
    }

    public void setDateRecharge(String dateRecharge) {
        this.dateRecharge = dateRecharge;
    }

    public int getNbKilos() {
        return nbKilos;
    }

    public void setNbKilos(int nbKilos) {
        this.nbKilos = nbKilos;
    }

    public UtilisateurDTO getUtilisateurDTO() {
        return utilisateurDTO;
    }

    public void setUtilisateurDTO(UtilisateurDTO utilisateurDTO) {
        this.utilisateurDTO = utilisateurDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
