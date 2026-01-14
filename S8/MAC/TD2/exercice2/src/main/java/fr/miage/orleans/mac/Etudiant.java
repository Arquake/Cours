package fr.miage.orleans.mac;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Etudiant {

    private String numero;
    private String nom;
    private Set<BiConsumer<Salle, Concert>> automatedActions;

    public Etudiant(String numero, String nom) {
        this.numero = numero;
        this.nom = nom;
        this.automatedActions = new HashSet<>();
    }

    public Etudiant() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void getNotification(Concert concert, Salle salle) {
        System.out.println(nom + " notifié !");
        automatedActions.forEach(action -> action.accept(salle, concert));
    }

    public void addAutomation(Salle salle, Consumer<Concert> action) {
        automatedActions.add((s, c)->{
            if (salle.equals(s)) {
                action.accept(c);
            }
        });
    }

    public void afficherConcert(Concert c) {
        System.out.println(c.getHeadline() + " affiché pour : " + nom);
    }
}
