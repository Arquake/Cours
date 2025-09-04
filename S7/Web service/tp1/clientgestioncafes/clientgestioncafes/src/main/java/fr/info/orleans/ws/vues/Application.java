package fr.info.orleans.ws.vues;

import fr.info.orleans.ws.controleur.Controleur;
import fr.info.orleans.ws.controleur.EcouteurOrdre;
import fr.info.orleans.ws.controleur.LanceurOrdre;
import fr.info.orleans.ws.controleur.Ordres;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Application implements VueInteractive, EcouteurOrdre {

    Controleur controleur;
    private Scene scene;


    public static Application creer(GestionnaireVue gestionnaireVue) {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.
                getResource("application.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Probleme de construction de vue Formulaire");
        }
        Application vue = fxmlLoader.getController();
        gestionnaireVue.ajouterVueInteractive(vue);
        gestionnaireVue.ajouterEcouteurOrdre(vue);
        vue.setScene(new Scene(vue.borderPane));
        return vue;
    }

    private void setScene(Scene scene) {
        this.scene = scene;
    }


    @FXML
    BorderPane borderPane;

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public Controleur getControleur() {
        return controleur;
    }


    @FXML
    TextField email, nom, prenom,cleSecrete;


    public void inscrire(ActionEvent actionEvent) {
        getControleur().inscrire(email.getText(), nom.getText(), prenom.getText());

    }


    public void valider(ActionEvent actionEvent) {
        getControleur().connexion(cleSecrete.getText());

    }

    @Override
    public void setAbonnement(LanceurOrdre g) {
        g.abonnement(this, Ordres.INSCRIPTION_OK, Ordres.ERREUR_INSCRIPTION_INCOMPLETE,Ordres.ERREUR_INSCRIPTION_EXISTANT, Ordres.ERREUR_CONNEXION);
    }

    @Override
    public void traiter(Ordres ordre) {
        switch (ordre) {
            case ERREUR_INSCRIPTION_INCOMPLETE:{
                Alert alert = new Alert(Alert.AlertType.ERROR,"Les champs email, nom et prénom sont obligatoires", ButtonType.OK);
                alert.showAndWait();
                break;
            }
            case ERREUR_INSCRIPTION_EXISTANT:{
                Alert alert = new Alert(Alert.AlertType.ERROR,"L'email est déjà utilisé !", ButtonType.OK);
                alert.showAndWait();
                break;
            }

            case ERREUR_CONNEXION: {
                Alert alert = new Alert(Alert.AlertType.ERROR,"La clé secrète est incorrecte", ButtonType.OK);
                alert.showAndWait();
                break;
            }

            case INSCRIPTION_OK:{
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Inscription OK", ButtonType.OK);
                alert.setContentText("Votre clé secrète à conserver est : "+controleur.getCleSecrete());
                alert.showAndWait();
                break;
            }
        }
    }

    public Scene getScene() {
        return scene;
    }
}
