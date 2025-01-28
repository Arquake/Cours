package facade;

import facade.exceptions.*;
import modele.Match;
import modele.Pari;
import modele.Utilisateur;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FacadeParisStaticImpl implements FacadeParis {

    private Map<String, Utilisateur> users = new HashMap<>();
    private Map<Long,Match> matchs = new HashMap<>();
    private Map<Long,Pari> paris = new HashMap<>();
    private List<Utilisateur> connectes = new ArrayList<>();

    public FacadeParisStaticImpl() {
        // initialise la "fausse" base de données
        Utilisateur u1 = Utilisateur.create("yo", "yo");
        Utilisateur u2 = Utilisateur.createAdmin("fred", "fred");
        users.put(u1.getLogin(), u1);
        users.put(u2.getLogin(), u2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        long m1 = 0;
        long m2 = 0;
        try {
            m1 = ajouterMatch("fred","foot","Allemagne", "France", LocalDateTime.now().plusMinutes(5));
            ajouterMatch("fred","rugby","France", "Australie", LocalDateTime.now().minusDays(7));
            m2 = ajouterMatch("fred","foot","France", "Australie", LocalDateTime.now().plusMonths(5));
            ajouterMatch("fred","foot","France", "Pérou", LocalDateTime.now().plusMonths(4));
            ajouterMatch("fred","foot","Danemark", "France", LocalDateTime.now().plusMonths(9));
        } catch (UtilisateurNonAdminException e) {
            e.printStackTrace();
        }
        try {
            parier("yo", m1, "nul", 35.0);
            parier("fred", m1, "France", 20.0);
            parier("yo", m2, "France", 110.0);
        } catch (MatchClosException e) {
            e.printStackTrace();
        } catch (ResultatImpossibleException e) {
            e.printStackTrace();
        } catch (MontantNegatifOuNulException e){
            e.printStackTrace();
        }

        // yohan a gagné son pari
        try {
            resultatMatch("fred",m1,"nul");
        } catch (UtilisateurNonAdminException e) {
            e.printStackTrace();
        } catch (ResultatImpossibleException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Utilisateur connexion(String login, String mdp) throws UtilisateurDejaConnecteException, InformationsSaisiesIncoherentesException {
        if (users.get(login) == null || !mdp.equals(users.get(login).getPassword())) {
            throw new InformationsSaisiesIncoherentesException();
        }
        Utilisateur user = users.get(login);
        if (connectes.contains(user)) {
            throw new UtilisateurDejaConnecteException();
        }
        connectes.add(user);
        return user;
    }

    @Override
    public Collection<Match> getMatchsPasCommences() {
        return matchs.values().stream().filter(match -> !match.isCommenceOuFini()).collect(Collectors.toList());
    }

    @Override
    public long parier(String login, long idMatch, String vainqueur, double montant) throws MatchClosException, ResultatImpossibleException, MontantNegatifOuNulException {
        Utilisateur parieur = users.get(login);
        Match match = matchs.get(idMatch);
        if (!("nul".equals(vainqueur)||match.getEquipe1().equals(vainqueur)||match.getEquipe2().equals(vainqueur))) {
            throw new ResultatImpossibleException(vainqueur);
        }

        if (montant <= 0) {
            throw new MontantNegatifOuNulException();
        }
        Pari pari = new Pari(parieur,match,vainqueur,montant);
        paris.put(pari.getIdPari(),pari);
        return pari.getIdPari();
    }

    @Override
    public void annulerPari(String login, long idPari) throws OperationNonAuthoriseeException {
        Utilisateur utilisateur = users.get(login);
        Pari pari = paris.get(idPari);
        // si l'utilisateur n'a pas les droits (pas admin et ce n'est pas lui le parieur)
        // ou si le match est commencé/passé,
        // on leve l'exception
        if (!(utilisateur.isAdmin()||login.equals(pari.getParieur().getLogin())) || pari.getMatch().getQuand().isBefore(LocalDateTime.now())) {
            throw new OperationNonAuthoriseeException("annulation de pari non authorisée pour "+login);
        }
        paris.remove(idPari);
    }

    @Override
    public Collection<Pari> getMesParis(String login) {
        return paris.values().stream()
                .filter(pari -> pari.getParieur().getLogin().equals(login))
                .collect(Collectors.toList());
    }

    @Override
    public long ajouterMatch(String login, String sport, String equipe1, String equipe2, LocalDateTime quand) throws UtilisateurNonAdminException {
        if (!users.get(login).isAdmin()) {
            throw new UtilisateurNonAdminException(login);
        }
        Match match = new Match(sport,equipe1,equipe2,quand);
        matchs.put(match.getIdMatch(), match);
        return match.getIdMatch();
    }

    @Override
    public void resultatMatch(String login, long idMatch, String resultat) throws UtilisateurNonAdminException, ResultatImpossibleException {
        if (!users.get(login).isAdmin()) {
            throw new UtilisateurNonAdminException(login);
        }
        Match match = matchs.get(idMatch);
        if (!("nul".equals(resultat)||match.getEquipe1().equals(resultat)||match.getEquipe2().equals(resultat))) {
            throw new ResultatImpossibleException(resultat);
        }
        match.setResultat(resultat);
        Stream<Pari> parisDuMatch = paris.values().stream().filter(pari -> pari.getMatch().getIdMatch()==idMatch);
        Double totalParisMatch = parisDuMatch.collect(Collectors.summingDouble(Pari::getMontant));
        parisDuMatch = paris.values().stream().filter(pari -> pari.getMatch().getIdMatch()==idMatch);
        Double totalParisMatchGagnants = parisDuMatch.filter(pari -> pari.getVainqueur().equals(resultat)).collect(Collectors.summingDouble(Pari::getMontant));
        Double ratioGain = (totalParisMatch*0.9)/totalParisMatchGagnants;
        Consumer<Pari> setGainsGagnant = pari -> pari.setGain(pari.getMontant()*ratioGain);
        Consumer<Pari> setGainsPerdant = pari -> pari.setGain(0.0);
        parisDuMatch = paris.values().stream().filter(pari -> pari.getMatch().getIdMatch()==idMatch);
        parisDuMatch.filter(pari -> pari.getVainqueur().equals(resultat)).forEach(setGainsGagnant);
        parisDuMatch = paris.values().stream().filter(pari -> pari.getMatch().getIdMatch()==idMatch);
        parisDuMatch.filter(pari -> !pari.getVainqueur().equals(resultat)).forEach(setGainsPerdant);
    }

    @Override
    public void deconnexion(String login) {
        Utilisateur user = users.get(login);
        connectes.remove(user);
    }

    @Override
    public Collection<Pari> getAllParis() {
        return paris.values();
    }

    @Override
    public Match getMatch(long idMatch) {
        return matchs.get(idMatch);
    }

    @Override
    public Pari getPari(long idPari) {
        return paris.get(idPari);
    }

    @Override
    public Collection<Match> getAllMatchs() {
        return matchs.values();
    }

}
