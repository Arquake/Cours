package affichages;

public class AffichageStats implements Affichage {
    private double tempMax = 0.0f;
    private double tempMin = 200;
    private double somTemp= 0.0f;
    private int nbLectures;

    public void afficher() {
        System.out.println("Température Moy/Max/Min = " + (somTemp / nbLectures)
                + "/" + tempMax + "/" + tempMin);
    }

    @Override
    public void actualiser(double temp, double humidite, double pression) {
        somTemp += temp;
        nbLectures++;
        if (temp > tempMax) {
            tempMax = temp;
        }
        if (temp < tempMin) {
            tempMin = temp;
        }
        afficher();
    }
}
