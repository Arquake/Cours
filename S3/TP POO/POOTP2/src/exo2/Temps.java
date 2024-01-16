package exo2;

public class Temps {
    private long heures;
    private int minutes, secondes;

    public Temps() {

    }

    public Temps(long heures, int minutes, int secondes) {
        this.heures = heures;
        this.minutes = minutes;
        this.secondes = secondes;
        normaliser();
    }

    public Temps(long t){
        heures = t/3600;
        t %= 3600;
        minutes = (int)t/60;
        secondes = (int)t%60;

    }

    public long getHeures() {
        return heures;
    }

    public void setHeures(long heures) {
        this.heures = heures;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
        normaliser();
    }

    public int getSecondes() {
        return secondes;
    }

    public void setSecondes(int secondes) {
        this.secondes = secondes;
        normaliser();
    }

    private void normaliser(){
        while( secondes > 59 ){
            secondes -= 60;
            minutes++;
        }
        while( minutes > 59 ){
            minutes -= 60;
            heures++;
        }
    }

    public long conversion(){
        return(heures*3600+minutes*60+secondes);
    }

    public void ajouterTemps(Temps t){
        heures += t.getHeures();
        minutes += t.getMinutes();
        secondes += t.getSecondes();
        normaliser();
    }
}
