package fr.miage.orleans.mac;

import java.time.LocalDateTime;

public class Programme {
    public static void main(String[] args) {
        Etudiant etu1 = new Etudiant("Anto", "o987654111");
        Etudiant etu2 = new Etudiant("Etu1", "o1");
        Etudiant etu3 = new Etudiant("Etu2", "o2");

        Salle astro = new Salle("Astrolabe");
        Salle paloma = new Salle("Paloma");
        Salle bouillon = new Salle("Bouillon");

        // etu 1 : tous les concerts
        astro.enregistrer(etu1);
        paloma.enregistrer(etu1);
        // etu 3 : que le bouillon
        astro.enregistrer(etu3);


        astro.sabonnerAuStyle(etu1, Style.FOLK);
        astro.addActionToUser(Action.AUTO_BUY, etu1);
        astro.addActionToUser(Action.AUTO_SHOW, etu1);
        astro.ajouterJourIndisponible(etu1, 2);

        astro.programmer( "SHANNON WRIGHT + JUNIPER", "", Style.FOLK, LocalDateTime.of(2025, 03, 04, 20, 00));
        astro.programmer( "A PLACE TO BURY STRANGERS + MADELINE GOLDSTEIN", "", Style.FOLK, LocalDateTime.of(2025, 04, 12, 20, 00));
        paloma.programmer("KOMPROMAT", "", Style.ELECTRO, LocalDateTime.of(2025, 11, 28, 20, 00));


    }
}
