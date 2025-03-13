package vues;

import controleur.ordres.EcouteurOrdre;
import controleur.ordres.LanceurOrdre;
import controleur.ordres.TypeOrdre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import modele.Cheval;
import vues.abstractVues.AbstractVueInteractive;

import java.io.IOException;

public class DetailCheval extends AbstractVueInteractive implements EcouteurOrdre {

    @FXML
    private BorderPane borderPane;


    @FXML
    private TextField idCheval;

    @FXML
    private TextField nomCheval;

    @FXML
    private TextField ageCheval;


    public static DetailCheval creer(GestionnaireVue gestionnaireVue) {
        FXMLLoader fxmlLoader = new FXMLLoader(DetailCheval.class.getResource("detailcheval.fxml"));
        try {
            fxmlLoader.load();
            DetailCheval vue = fxmlLoader.getController();
            vue.initialisation();
            gestionnaireVue.ajouterVueInteractive(vue);
            gestionnaireVue.ajouterEcouteurOrdre(vue);
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("Soucis de chargement du fichier : detaicheval.fxml");
        }

    }




    @Override
    public Parent getTopParent() {
        return borderPane;
    }


    public void gotochevaux(ActionEvent actionEvent) {
        this.getControleur().goToChevaux();
    }

    @Override
    public void setAbonnement(LanceurOrdre g) {
        g.abonnement(this,TypeOrdre.SELECTION_CHEVAL);
    }

    @Override
    public void traiter(TypeOrdre e) {
        if (e == TypeOrdre.SELECTION_CHEVAL) {
            Cheval cheval = this.getControleur().getChevalSelectionne();
            this.nomCheval.setText(cheval.getNomCheval());
            this.idCheval.setText(Long.toString(cheval.getId()));
            this.ageCheval.setText(Integer.toString(cheval.getAge()));
        }
    }
}
