package vues;

import controleur.ordres.LanceurOrdre;
import controleur.ordres.TypeOrdre;
import javafx.stage.Stage;
import vues.abstractVues.AbstractGestionnaireVue;

public class GestionnaireVueImpl extends AbstractGestionnaireVue {


    private AjoutCheval ajoutCheval;
    private Chevaux chevaux;
    private DetailCheval detailCheval;


    public GestionnaireVueImpl(Stage stage) {
        super(stage);
        ajoutCheval = AjoutCheval.creer(this);
        chevaux = Chevaux.creer(this);
        detailCheval = DetailCheval.creer(this);
    }

    @Override
    public void setAbonnement(LanceurOrdre g) {
        g.abonnement(this,TypeOrdre.SHOW_CHEVAUX,TypeOrdre.SHOW_DETAIL,TypeOrdre.SHOW_ENREGISTREMENT);
    }

    @Override
    public void traiter(TypeOrdre e) {
        switch (e) {
            case SHOW_CHEVAUX: {
                this.getStage().setScene(this.chevaux.getScene());
                this.getStage().show();
                break;
            }

            case SHOW_DETAIL: {
                this.getStage().setScene(this.detailCheval.getScene());
                this.getStage().show();

                break;
            }


            case SHOW_ENREGISTREMENT: {
                this.getStage().setScene(this.ajoutCheval.getScene());
                this.getStage().show();

                break;
            }

        }
    }
}
