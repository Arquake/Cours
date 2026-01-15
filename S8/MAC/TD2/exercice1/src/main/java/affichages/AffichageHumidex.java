package affichages;

public class AffichageHumidex implements Affichage {

    private double humidex;

    @Override
    public void actualiser(double temp, double humidite, double pression) {
        this.humidex = temp * humidite;
    }

    public void afficher() {
        System.out.println("Conditions ressenti: " + humidex + "°C");
    }
}
