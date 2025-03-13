package vues;

import controleur.ordres.EcouteurOrdre;
import controleur.ordres.LanceurOrdre;
import controleur.ordres.TypeOrdre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import vues.abstractVues.AbstractVueInteractive;

import java.io.IOException;

public class AjoutCheval extends AbstractVueInteractive implements EcouteurOrdre {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField nomCheval;

    @FXML
    private TextField ageCheval;


    public static AjoutCheval creer(GestionnaireVue gestionnaireVue) {
        FXMLLoader fxmlLoader = new FXMLLoader(AjoutCheval.class.getResource("ajoutcheval.fxml"));
        try {
            fxmlLoader.load();
            AjoutCheval vue = fxmlLoader.getController();
            vue.initialisation();
            gestionnaireVue.ajouterVueInteractive(vue);
            gestionnaireVue.ajouterEcouteurOrdre(vue);
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("Soucis de chargement du fichier : ajoutcheval.fxml");
        }

    }

    @Override
    public Parent getTopParent() {
        return borderPane;
    }

    public void enregistrer(ActionEvent actionEvent) {

        try {
            String nom = this.nomCheval.getText();
            int age = Integer.valueOf(this.ageCheval.getText());

            if (nom.isEmpty() || age <=0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Problème au niveau des données");
                alert.setContentText("Le nom doit être non vide et l'âge un entier > 0 !!");
                alert.showAndWait();
            }
            else {
                this.getControleur().enregistrerCheval(nom,age);
            }

        }
        catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Problème au niveau de l'âge");
            alert.setContentText("Veuillez saisir un entier en guise d'âge !!");
            alert.showAndWait();
        }


    }

    public void gotochevaux(ActionEvent actionEvent) {
        this.getControleur().goToChevaux();
    }

    @Override
    public void setAbonnement(LanceurOrdre g) {
        g.abonnement(this,TypeOrdre.NOUVEAU_CHEVAL,TypeOrdre.ERREUR_CHEVAL_EXISTANT);
    }

    @Override
    public void traiter(TypeOrdre e) {
        if (e == TypeOrdre.NOUVEAU_CHEVAL) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cheval ajouté en base ");
            alert.setContentText("Le cheval a bien été enregistré");
            alert.showAndWait();
            this.ageCheval.setText("");
            this.nomCheval.setText("");

        }

        if (e == TypeOrdre.ERREUR_CHEVAL_EXISTANT) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Conflit de noms ");
            alert.setContentText("Un cheval existe déjà sous ce nom :"+this.nomCheval.getText());
            alert.showAndWait();
        }
    }
}
