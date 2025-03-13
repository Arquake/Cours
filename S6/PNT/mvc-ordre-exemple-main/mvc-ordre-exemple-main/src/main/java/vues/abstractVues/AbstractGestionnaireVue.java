package vues.abstractVues;

import controleur.ordres.EcouteurOrdre;
import javafx.stage.Stage;
import vues.GestionnaireVue;
import vues.VueInteractive;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractGestionnaireVue implements GestionnaireVue {
    private Stage stage;

    @Override
    public Stage getStage() {
        return stage;
    }

    private Collection<EcouteurOrdre> ecouteurOrdres;

    private Collection<VueInteractive> vuesInteractives;

    public AbstractGestionnaireVue(Stage stage) {

        this.stage = stage;
        this.ecouteurOrdres = new ArrayList<>();
        this.vuesInteractives = new ArrayList<>();
        this.ecouteurOrdres.add(this);
    }

    @Override
    public void ajouterEcouteurOrdre(EcouteurOrdre ecouteurOrdre) {
        this.ecouteurOrdres.add(ecouteurOrdre);
    }

    @Override
    public Collection<EcouteurOrdre> getEcouteurOrdres() {
        return ecouteurOrdres;
    }


    @Override
    public void ajouterVueInteractive(VueInteractive vueInteractive) {
        this.vuesInteractives.add(vueInteractive);
    }

    @Override
    public Collection<VueInteractive> getVuesInteractives() {
        return this.vuesInteractives;
    }
}
