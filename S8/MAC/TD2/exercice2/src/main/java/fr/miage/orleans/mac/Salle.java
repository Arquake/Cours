package fr.miage.orleans.mac;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Salle {

    private String nom;

    private Map<Etudiant, PersonnalFilters> abonnees;
    private Set<Concert> concerts;

    public Salle() {
        abonnees = new HashMap<>();
        concerts = new HashSet<>();
    }

    public Salle(String nom) {
        this.nom = nom;
        abonnees = new HashMap<>();
        concerts = new HashSet<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void enregistrer(Etudiant etu) {
        abonnees.put(etu, new PersonnalFilters());
    }

    public void programmer(String headline, String idkKms, Style style, LocalDateTime dateConcert) {
        Concert c = new Concert(headline,style, dateConcert);
        concerts.add(c);
        abonnees.entrySet().forEach(entries -> {
            if (!entries.getValue().getWantedStyles().contains(style) || entries.getValue().getUnwantedDays().contains(dateConcert.getDayOfWeek().getValue())) return;
            entries.getKey().getNotification(c, this);
        });
    }

    public void sabonnerAuStyle(Etudiant etu, Style style) {
        if (!abonnees.containsKey(etu)) return;
        abonnees.get(etu).addWantedStyles(style);
    }

    public void ajouterJourIndisponible(Etudiant etu, Integer dayOfTheWeek) {
        abonnees.get(etu).addUnwantedDays(dayOfTheWeek);
    }

    public void addActionToUser(Action a, Etudiant etu) {
        Consumer<Concert> s = switch (a) {
            case AUTO_BUY -> (c) -> this.buySeat(etu, c);
            case AUTO_SHOW -> etu::afficherConcert;
            default -> null;
        };
        if (Objects.isNull(s)) return;
        etu.addAutomation(this, s);
    }

    public void buySeat(Etudiant etu, Concert c) {
        System.out.println(etu.getNom() + " à acheté une place pour " + c.getHeadline());
    }
}
