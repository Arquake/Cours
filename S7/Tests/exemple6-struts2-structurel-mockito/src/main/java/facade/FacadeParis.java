package facade;

import facade.exceptions.*;
import modele.Match;
import modele.Pari;
import modele.Utilisateur;

import java.time.LocalDateTime;
import java.util.Collection;

public interface FacadeParis {
    Utilisateur connexion(String var1, String var2) throws UtilisateurDejaConnecteException, InformationsSaisiesIncoherentesException;

    Collection<Match> getMatchsPasCommences();

    long parier(String var1, long var2, String var4, double var5) throws MatchClosException, ResultatImpossibleException;

    void annulerPari(String var1, long var2) throws OperationNonAuthoriseeException;

    Match getMatch(long var1);

    Pari getPari(long var1);

    Collection<Pari> getMesParis(String var1);

    long ajouterMatch(String var1, String var2, String var3, String var4, LocalDateTime var5) throws UtilisateurNonAdminException;

    void resultatMatch(String var1, long var2, String var4) throws UtilisateurNonAdminException, ResultatImpossibleException;

    void deconnexion(String var1);

    Collection<Pari> getAllParis();

    Collection<Match> getAllMatchs();
}
