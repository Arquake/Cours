package actions;

import modele.Match;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AjouterMatch extends Environnement {
    // en entrée
    private String sport;
    private String equipe1;
    private String equipe2;
    private String quand;
    // en sortie
    private Match match;

    @Override
    public String execute() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime quandDF;
        try {
            quandDF = LocalDateTime.parse(quand, formatter);
        } catch (DateTimeParseException e) {
            addFieldError("quand", "mauvais format de date.");
            return INPUT;
        }
        if (quandDF.isBefore(LocalDateTime.now())) {
            addFieldError("quand", "la date doit être postérieure à aujourd'hui.");
            return INPUT;
        }
        String login = getUtilisateur().getLogin();
        long matchId = getFacade().ajouterMatch(login, sport, equipe1, equipe2, quandDF);
        match = getFacade().getMatch(matchId);
        return SUCCESS;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setEquipe1(String equipe1) {
        this.equipe1 = equipe1;
    }

    public void setEquipe2(String equipe2) {
        this.equipe2 = equipe2;
    }

    public void setQuand(String quand) {
        this.quand = quand;
    }

    public Match getMatch() {
        return match;
    }

    @Override
    public void validate() {
        if (sport==null || sport.equals("")) {
            addFieldError("sport", "ne peut être vide.");
        }
        if (equipe1==null || equipe1.equals("")) {
            addFieldError("equipe1", "ne peut être vide.");
        }
        if (equipe2==null || equipe2.equals("")) {
            addFieldError("equipe2", "ne peut être vide.");
        }
        if (equipe1!=null && equipe2!=null && equipe1.equals(equipe2)) {
            addFieldError("equipe2", "ne peut être égal à équipe 1.");
        }
    }
}
