package fr.info.orleans.ws.vues;

import fr.info.orleans.ws.controleur.LanceurOrdre;
import fr.info.orleans.ws.controleur.Ordres;
import javafx.stage.Stage;

public class GestionnaireVueImpl extends GestionnaireVue {

    Application application;
    GestionCafes gestionCafes;


    public GestionnaireVueImpl(Stage stage) {
        super(stage);
        application = Application.creer(this);
        gestionCafes = GestionCafes.creer(this);
    }

    @Override
    public void setAbonnement(LanceurOrdre g) {
        super.setAbonnement(g);
        g.abonnement(this,
                Ordres.SHOW_MENU,
                Ordres.SHOW_GESTION);

    }

    @Override
    public void traiter(Ordres e) {
        switch (e) {
            case SHOW_GESTION:{
                this.getStage().setScene(gestionCafes.getScene());
                break;
            }

            case SHOW_MENU: {
                this.getStage().setScene(application.getScene());
                break;
            }
        }
        this.getStage().show();

    }
}
