package exo3;

public interface Ordonnable <T> extends Comparable<T>{
    int compareTo(T o);
    boolean estPlusGrand(T o);
    boolean estPlusPetit(T o);
    boolean estEgal(T o);
}
