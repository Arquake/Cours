package exo5;

public class Horaire implements Comparable<Horaire> {
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

    public boolean equals ( Object o ){
        if( o == null ){ return false; }
        if( o.getClass() != this.getClass() ) { return false; }
        if( this == o ) { return true; }
        if( ((Horaire) o).getMinutes() == minutes){ return true; }
        return false;
    }

    public String toString (){
        return (minutes/60)%24+"h"+minutes%60+"mm";
    }

    @Override
    public int compareTo(Horaire o) {
        if ( this.minutes < o.minutes ){ return -1; }
        if ( this.minutes == o.minutes ){ return 0; }
        return 1;
    }
}
