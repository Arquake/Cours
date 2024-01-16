package exo4;

public class Horaire {
    private int minutes;
    public Horaire(int minutes) {
        this.minutes = minutes;
    }

    public int duree (Horaire fin){
        return (fin.getMinutes()-this.minutes);
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public boolean equals(Object obj) {
        if ( obj == this ){ return true; }
        if ( obj == null ){ return false; }
        if ( obj.getClass() != this.getClass() ){ return false; }
        return this.minutes == ((Horaire) obj).minutes;
    }

    @Override
    public String toString(){
        return minutes/60+"h"+minutes%60+"mn";
    }
}
