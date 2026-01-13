package affichages;

public class AffichagePrevisionsPression implements Affichage {
    private double pression;
    private double pression_courante = 1012;

    @Override
    public void actualiser(double temp, double humidite, double pression) {
        this.pression = pression;

        afficher();
    }

    public void afficher() {
        System.out.print("Prévision : ");
        if (this.pression_courante > this.pression) {
            System.out.println("Amélioration en cours !");
        } else if (this.pression_courante == this.pression) {
            System.out.println("Le temps se rafraîchit");
        } else if (this.pression_courante < this.pression) {
            System.out.println("Dépression bien installée");
        }
    }
}
