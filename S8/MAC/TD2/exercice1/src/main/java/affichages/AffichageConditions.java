package affichages;

public class AffichageConditions implements Affichage {

    private double temperature;
    private double humidite;
    private double pression;

    @Override
    public void actualiser(double temp, double humidite, double pression) {
        this.temperature = temp;
        this.humidite = humidite;
        this.pression = pression;
    }

    public void afficher() {
        System.out.println("Conditions actuelles: " + temperature
                + " °C ; pression " + pression + " et " + humidite + "% d'humidité");
    }
}
