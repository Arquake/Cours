package programme;

import affichages.AffichageConditions;
import affichages.AffichageHumidex;
import affichages.AffichagePrevisionsPression;
import affichages.AffichageStats;
import modele.DonneesMeteo;

public class Programme {
    public static void main(String[] args) {
        AffichageConditions affichageCond = new AffichageConditions();
        AffichageStats affichageStat = new AffichageStats();
        AffichagePrevisionsPression affichagePrev = new AffichagePrevisionsPression();
        AffichageHumidex affichageHumidex = new AffichageHumidex();

        DonneesMeteo donneesMeteo = new DonneesMeteo("station1", affichageCond, affichagePrev, affichageStat, affichageHumidex);

        donneesMeteo.setTemperature(26);
        donneesMeteo.setPression(1020);
        donneesMeteo.setHumidite(70);
        donneesMeteo.actualiserMesures();
        //
        affichageCond.afficher();
        affichagePrev.afficher();
        affichageStat.afficher();
        affichageHumidex.afficher();

        donneesMeteo.setPression(1012);
        donneesMeteo.setTemperature(25);
        donneesMeteo.setTemperature(23);
        donneesMeteo.actualiserMesures();
        //
        affichageCond.afficher();
        affichagePrev.afficher();
        affichageStat.afficher();
        affichageHumidex.afficher();
    }
}
