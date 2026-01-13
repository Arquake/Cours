package fr.miage.orleans.mac;

import java.time.LocalDateTime;
import java.util.*;

public class Salle {

    private String nom;

    private Map<Etudiant, Set<Style>> abonnees;
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
        abonnees.put(etu, new HashSet<>());
    }

    public void programmer(String headline, String idkKms, Style style, LocalDateTime dateConcert) {
        Concert c = new Concert(headline,style, dateConcert);
        concerts.add(c);
        abonnees.entrySet().forEach(entries -> {
            if (entries.getValue().contains(style)) {
                entries.getKey().getNotification(c);
            }
        });
    }

    public void sabonnerAuStyle(Etudiant etu, Style style) {
        if (!abonnees.containsKey(etu)) return;
        abonnees.get(etu).add(style);
    }
}
