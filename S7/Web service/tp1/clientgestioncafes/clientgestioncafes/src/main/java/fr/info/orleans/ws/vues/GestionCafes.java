package fr.info.orleans.ws.vues;

import fr.info.orleans.ws.controleur.Controleur;
import fr.info.orleans.ws.controleur.EcouteurOrdre;
import fr.info.orleans.ws.controleur.LanceurOrdre;
import fr.info.orleans.ws.controleur.Ordres;
import fr.info.orleans.ws.modele.RechargeCafeDTO;
import fr.info.orleans.ws.modele.UtilisateurDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class GestionCafes implements VueInteractive, EcouteurOrdre {


    @FXML
    TextField nbKilos;
    @FXML
    DatePicker dateDebut;

    @FXML
    DatePicker dateFin;

    @FXML
    CheckBox perso;


    @FXML
    BorderPane borderPane;


    @FXML
    ListView<RechargeCafeDTO> recharges;

    Controleur controleur;


    private void configListView() {

        recharges.setCellFactory(param -> new ListCell<RechargeCafeDTO>() {
            @Override
            protected void updateItem(RechargeCafeDTO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null ) {
                    setText("");
                } else {
                    setText(item.getDateRecharge()+" : "+item.getNbKilos()+" kg"+ " par "
                    + item.getUtilisateurDTO().getPrenom() + " " + item.getUtilisateurDTO().getNom());
                }
            }
        });

    }


    public static GestionCafes creer(GestionnaireVue gestionnaireVue) {
        FXMLLoader fxmlLoader = new FXMLLoader(GestionCafes.class.
                getResource("gestioncafes.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Probleme de construction de vue Formulaire");
        }
        GestionCafes vue = fxmlLoader.getController();
        gestionnaireVue.ajouterVueInteractive(vue);
        gestionnaireVue.ajouterEcouteurOrdre(vue);
        vue.setScene(new Scene(vue.borderPane));
        vue.configListView();
        return vue;

    }

    private Scene scene;
    public void setScene(Scene scene) {
        this.scene = scene;
    }


    public Scene getScene() {
        return scene;
    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public Controleur getControleur() {
        return controleur;
    }

    @Override
    public void setAbonnement(LanceurOrdre g) {
        g.abonnement(this,Ordres.ERREUR_FORMAT_DATE,Ordres.ERREUR_NB_KILOS,Ordres.RECHARGE_CREATION_OK, Ordres.DATA_UPDATED);
    }

    @Override
    public void traiter(Ordres ordre) {
        switch (ordre) {
            case ERREUR_FORMAT_DATE: {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Date invalide", ButtonType.OK);
                alert.setContentText("La date doit être au format dd-MM-YYYY");
                alert.showAndWait();
                break;
            }
            case ERREUR_NB_KILOS: {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Nb kilos invalide", ButtonType.OK);
                alert.setContentText("Le nombre de kilos doit être un entier strictement positif");
                alert.showAndWait();

                break;
            }


            case DATA_UPDATED: {
                majListView();
                break;
            }
//            case ERREUR_MAJ: {
//                Alert alert = new Alert(Alert.AlertType.ERROR,"Erreur MAJ", ButtonType.OK);
//                alert.setContentText("Vous tentez de modifier une recharge qui n'est pas la vôtre !");
//                alert.showAndWait();
//                break;
//            }

            case RECHARGE_CREATION_OK :{
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Confirmation création", ButtonType.OK);
                alert.setContentText("Recharge créée !");
                alert.showAndWait();
                break;
            }

//            case RECHARGE_MAJ_OK : {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Confirmation modification", ButtonType.OK);
//                alert.setContentText("Modification prise en compte !");
//                alert.showAndWait();
//                break;
//            }
        }

    }

    private void majListView() {
        recharges.getItems().clear();
        recharges.getItems().addAll(controleur.getRecharges());



    }

    public void appliquer(ActionEvent actionEvent) {
        this.controleur.filtrer(perso.isSelected(),dateDebut.getValue(),dateFin.getValue());
    }


    public void creerRecharge(ActionEvent actionEvent) {
        int nbKilos = 0;
        try {
            nbKilos = Integer.parseInt(this.nbKilos.getText());


            this.controleur.creerRecharge(nbKilos);
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Nb kilos invalide", ButtonType.OK);
            alert.setContentText("Le nombre de kilos doit être un entier strictement positif");
            alert.showAndWait();
        }



    }
}
