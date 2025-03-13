package vues;

import controleur.ordres.EcouteurOrdre;
import controleur.ordres.LanceurOrdre;
import controleur.ordres.TypeOrdre;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import modele.Cheval;
import vues.abstractVues.AbstractVueInteractive;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Chevaux extends AbstractVueInteractive implements EcouteurOrdre {
    @FXML
    private BorderPane borderPane;

    @FXML
    private ListView<Cheval> chevaux;


    private void costumListView() {
        this.chevaux.setCellFactory(ls -> {
            TextFieldListCell<Cheval> textFieldListCell = new TextFieldListCell<>();
            textFieldListCell.setConverter(new StringConverter<Cheval>() {
                @Override
                public String toString(Cheval cheval) {
                    if (Objects.isNull(cheval)) {
                        return "";
                    }
                    else {
                        return cheval.getId() + " - " + cheval.getNomCheval();
                    }
                }

                @Override
                public Cheval fromString(String s) {
                    return null;
                }
            });
            return textFieldListCell;
        });

    }


    public static Chevaux creer(GestionnaireVue gestionnaireVue) {
        FXMLLoader fxmlLoader = new FXMLLoader(Chevaux.class.getResource("chevaux.fxml"));
        try {
            fxmlLoader.load();
            Chevaux vue = fxmlLoader.getController();
            vue.initialisation();
            vue.costumListView();
            gestionnaireVue.ajouterVueInteractive(vue);
            gestionnaireVue.ajouterEcouteurOrdre(vue);
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("Soucis de chargement du fichier : chevaux.fxml");
        }

    }




    @Override
    public Parent getTopParent() {
        return borderPane;
    }


    public void selectioncheval(MouseEvent mouseEvent) {

        if (mouseEvent.getClickCount() == 2) {
            ListView<Cheval> l = (ListView<Cheval>) mouseEvent.getSource();
            Cheval cheval = l.getSelectionModel().getSelectedItem();
            if (Objects.nonNull(cheval)) {
                this.getControleur().goToDetailCheval(cheval.getId());
            }
        }


    }

    public void gotoajoutercheval(ActionEvent actionEvent) {
        this.getControleur().goToAjouterCheval();

    }

    @Override
    public void setAbonnement(LanceurOrdre g) {
        g.abonnement(this,TypeOrdre.LOAD_CHEVAUX,TypeOrdre.NOUVEAU_CHEVAL);
    }

    @Override
    public void traiter(TypeOrdre e) {
        if (e == TypeOrdre.LOAD_CHEVAUX || e == TypeOrdre.NOUVEAU_CHEVAL) {
            List<Cheval> lesChevaux = this.getControleur().getChevaux();
            this.chevaux.setItems(FXCollections.observableList(lesChevaux));
        }
    }
}


