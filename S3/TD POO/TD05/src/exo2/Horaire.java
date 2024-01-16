package exo2;

public class Horaire {
    private int heures;
    private int minutes;
    public Horaire(int heures, int minutes) throws Exception, Exception {
        if ( heures > 23 ) { throw new InstantiationException("hours too high : "+heures); }
        if ( minutes > 59 ) { throw new InstantiationException("minutes too high : "+minutes); }
        this.heures= heures;
        this.minutes = minutes;
    }
    public int getHeures() { return heures;}
    public int getMinutes() { return minutes;}
}
