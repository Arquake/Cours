package fr.info.orleans.ws.vues;
import fr.info.orleans.ws.controleur.Controleur;
import fr.info.orleans.ws.controleur.EcouteurOrdre;
import fr.info.orleans.ws.controleur.LanceurOrdre;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;

public abstract class GestionnaireVue implements EcouteurOrdre {
    private Stage stage;
    private Collection<EcouteurOrdre> ecouteursOrdres;
    private Collection<VueInteractive> vuesInteractives;
    public Stage getStage() {
        return stage;
    }
    public GestionnaireVue(Stage stage) {
        this.stage = stage;
        this.ecouteursOrdres = new ArrayList<>();
        this.vuesInteractives = new ArrayList<>();

    }

    @Override
    public void setAbonnement(LanceurOrdre g) {
        this.ecouteursOrdres.stream()
                .forEach(e -> {e.setAbonnement(g);});

    }
    public Collection<VueInteractive> getvueInteractives(){
        return vuesInteractives;
    }

    public void ajouterVueInteractive(VueInteractive vueInteractive) {
        this.vuesInteractives.add(vueInteractive);
    }

    public void ajouterEcouteurOrdre(EcouteurOrdre ecouteurOrdre) {
        this.ecouteursOrdres.add(ecouteurOrdre);
    }


    public void setControleur(Controleur c) {
        this.vuesInteractives.stream()
                .forEach(v -> {v.setControleur(c);});
    }
}

