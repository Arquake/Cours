package exo2;

public class Reunion<T> implements Comparable<T> {
    private String sujet;
    private Horaire debut;
    private Horaire fin;

    public Reunion(String sujet, Horaire debut, Horaire fin) {
        this.sujet = sujet;
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public int compareTo(T o) {
        if( this == o ) { return 1; }
        if( o == null ) { return 0; }
        if( this.getClass() != o.getClass() ) { return 0; }
        if((this.sujet == ((Reunion<?>) o).sujet) && (this.debut == ((Reunion<?>) o).debut) && (this.fin == ((Reunion<?>) o).fin)){ return 1; }
        return 0;
    }
}
