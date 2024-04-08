package modele.controleur;

import modele.AgenceBancaire;
import modele.Journalisation;
import modele.vue.Console;

public class Controleur {
    private final Console console = new Console();

    private final AgenceBancaire agence = new AgenceBancaire();

    public Controleur() {
        Journalisation journal = new Journalisation();
        int numCompte = 0;
        String operation = "";
        int montant = 0;
        System.out.println(agence);
        do {
            try {
                numCompte = console.demanderNumCompte();
                operation = console.demanderOperation();
                montant = console.demanderMontant();
                agence.gererOperation(numCompte, operation, montant);
                journal.ajouterLog(numCompte + operation + montant);
                console.afficherInformation(journal.getLog());
            } catch (Exception e) {
                console.afficherInformation(e.getMessage());
                journal.ajouterLogErr(numCompte + operation + montant);
            }
        } while (!console.demanderQuitter().equalsIgnoreCase("Q"));
        console.showLog(journal.getLastLog(), journal.getLastLog()? journal.getLog() : journal.getLogErr());
    }
}
