package vues;

import javafx.stage.Stage;
import ordres.LanceurOrdre;
import ordres.TypeOrdre;

import java.util.ArrayList;

public class GestionnaireVueImpl extends AbstractGestionnaireVue {


    private Menu menu;
    private TousLesFilms tousLesFilms;
    private Ajout ajout;
    private Rechercher rechercher;
    private FilmInfo filmInfo;

    public GestionnaireVueImpl(Stage stage) {
        super(stage);
        menu = Menu.creerVue(this);
        tousLesFilms = TousLesFilms.creerVue(this);
        ajout = Ajout.creerVue(this);
        rechercher = Rechercher.creerVue(this);
        filmInfo = FilmInfo.creerVue(this);
    }

    @Override
    public void setAbonnement(LanceurOrdre g) {
        g.abonnement(this,
                TypeOrdre.NOUVEAU_FILM,
                TypeOrdre.LOAD_FILMS,
                TypeOrdre.SHOW_FILM,
                TypeOrdre.SHOW_FILMS,
                TypeOrdre.SHOW_MENU,
                TypeOrdre.SHOW_RECHERCHE,
                TypeOrdre.SHOW_FILMS_RECHERCHE,
                TypeOrdre.SHOW_AJOUT,
                TypeOrdre.GENRE_INCONNU_ERROR,
                TypeOrdre.NOM_DEJA_EXISTANT_ERROR,
                TypeOrdre.CHAMP_VIDE_ERROR,
                TypeOrdre.GENRE_INCONNU_RECHERCHE_ERROR
        );
    }

    @Override
    public void traiter(TypeOrdre e) {
        switch (e) {
            case SHOW_FILM: {
                this.getStage().setScene(this.filmInfo.getScene());
                this.getStage().show();
                break;
            }

            case SHOW_FILMS_RECHERCHE:
            case SHOW_FILMS: {
                this.getStage().setScene(this.tousLesFilms.getScene());
                this.getStage().show();

                break;
            }

            case SHOW_MENU: {
                this.getStage().setScene(this.menu.getScene());
                this.getStage().show();
                break;
            }

            case SHOW_RECHERCHE: {
                this.getStage().setScene(this.rechercher.getScene());
                this.getStage().show();

                break;
            }

            case SHOW_AJOUT: {
                this.getStage().setScene(this.ajout.getScene());
                this.getStage().show();

                break;
            }

            case NOUVEAU_FILM: {

            }
        }
    }
}