package modele;

import java.time.LocalDateTime;
import java.util.Optional;

public class Match {
    private long idMatch;
    private String sport;
    private String equipe1;
    private String equipe2;
    private LocalDateTime quand;
    private Optional<String> resultat; // equipe1 ou equipe2 ou empty

    private static long lastId = 0L;

    public Match(String sport, String equipe1, String equipe2, LocalDateTime quand) {
        this.idMatch = ++lastId;
        this.sport = sport;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.quand = quand;
        this.resultat = Optional.empty();
    }

    public boolean isCommenceOuFini() {
        return quand.isBefore(LocalDateTime.now());
    };

    public long getIdMatch() {
        return idMatch;
    }

    public String getSport() {
        return sport;
    }

    public String getEquipe1() {
        return equipe1;
    }

    public String getEquipe2() {
        return equipe2;
    }

    public LocalDateTime getQuand() {
        return quand;
    }

    public Optional<String> getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = Optional.of(resultat);
    }

    public static long getLastId() {
        return lastId;
    }

    @Override
    public String toString() {
        return "Match{" +
                "idMatch=" + idMatch +
                ", sport='" + sport + '\'' +
                ", equipe1='" + equipe1 + '\'' +
                ", equipe2='" + equipe2 + '\'' +
                ", quand=" + quand +
                ", resultat=" + resultat +
                '}';
    }
}
