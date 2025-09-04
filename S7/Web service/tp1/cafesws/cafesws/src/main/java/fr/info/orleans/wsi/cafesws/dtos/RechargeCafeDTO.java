package fr.info.orleans.wsi.cafesws.dtos;

import java.io.Serializable;
import java.util.UUID;

public class RechargeCafeDTO implements Serializable {

    private String dateRecharge;
    private int nbKilos;
    private UtilisateurDTO utilisateurDTO;
    private String id;


    public RechargeCafeDTO(String id, String dateRecharge, int nbKilos, UtilisateurDTO utilisateurDTO) {
        this.dateRecharge = dateRecharge;
        this.nbKilos = nbKilos;
        this.utilisateurDTO = utilisateurDTO;
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RechargeCafeDTO() {
    }


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
}
