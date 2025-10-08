package actions;

import modele.TokenExpireException;
import modele.exceptions.*;
import outils.DateBuilderFromString;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class SaisirNouveauBonPlan extends Environnement {

    private String description;
    private String thematique;
    private String dateDebut;
    private String dateFin;
    private String lien;
    private double prix;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThematique() {
        return thematique;
    }

    public void setThematique(String thematique) {
        this.thematique = thematique;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String execute()   {

        try {
            LocalDateTime debut = DateBuilderFromString.builder.stringToLocalDateTime(dateDebut);
            LocalDateTime fin = DateBuilderFromString.builder.stringToLocalDateTime(dateFin);
            getFacadeBonsPlans().creerBonPlan(getToken(), description, thematique, lien, prix, debut, fin);
            addActionMessage("Le bon plan a bien été créé !");
            return SUCCESS;
        }
        catch (DateTimeParseException e){
            addActionError("La date de début ou de fin n'est pas valide");
            return INPUT;
        } catch (DatesIncoherentesException e) {
            addActionError("La date de fin doit être supérieure à la date de début");
            return INPUT;
        } catch (TokenExpireException e) {
            addActionError("Token expire");
            return ERROR;
        } catch (UtilisateurInconnuException e) {
            addActionError("Token inconnu");
            return ERROR;
        } catch (ParametresManquantsException e) {
            addActionError("Tous les champs sont obligatoires");
            return INPUT;
        } catch (PrixIncoherentException e) {
            addActionError( "Le prix doit être supérieur à 0");
            return INPUT;
        }

    }
}
