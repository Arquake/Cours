package actions;

import modele.BonPlan;

import modele.TokenExpireException;
import modele.exceptions.*;
import outils.DateBuilderFromString;
import outils.DateConvertisseur;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Optional;

public class ModifierBonPlan extends Environnement {

    private String description;
    private String theme;
    private String dateDebut;
    private String dateFin;
    private String lien;
    private double prix;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    BonPlan bonPlan;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThematique() {
        return theme;
    }

    public void setThematique(String theme) {
        this.theme = theme;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }



    public Collection<BonPlan> getBonsPlans() throws TokenExpireException, UtilisateurInconnuException
    {
        return getFacadeBonsPlans().getBonsPlans(getToken(), Optional.empty());
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
    public String execute() throws BonPlanInconnuException {

        try {
            LocalDateTime debut = DateBuilderFromString.builder.stringToLocalDateTime(dateDebut);
            LocalDateTime fin = DateBuilderFromString.builder.stringToLocalDateTime(dateFin);
            bonPlan = getFabriqueBonPlan().creer(id, description, theme, lien, prix, debut, fin);
            getFacadeBonsPlans().modifierBonPlan(getToken(), bonPlan);
            addActionMessage("Le bon plan a bien été modifié");
            return SUCCESS;
        }
        catch (DateTimeParseException e){
            addActionError("La date de début ou de fin n'est pas valide");
            return INPUT;
        } catch (TokenExpireException e) {
            addActionError("Token expire");
            return ERROR;
        } catch (UtilisateurInconnuException e) {
            addActionError("Token inconnu");
            return ERROR;
        } catch (DroitsInsuffisantsException e) {
            throw new RuntimeException(e);
        }

    }
}
