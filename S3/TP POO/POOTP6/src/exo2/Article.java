package exo2;

import java.util.Comparator;
import java.util.Objects;

public class Article implements Comparable, Comparator {

    int numero;

    static int currentNumber = 0;

    public Article(String description) {
        this.numero = currentNumber;
        currentNumber += 1;
        this.description = description;
    }

    String description;

    // number compare

    //@Override
    //public int compareTo(Object o) {
    //    if( o == null ){ return -1; }
    //    if( o == this ){ return 0; }
    //    if( o.getClass() != this.getClass() ){ return -1; }
    //    if( ((Article) o).numero == this.numero ){ return 0; }
    //    if( ((Article) o).numero < this.numero ){ return 1; }
    //    return -1;
    //}

    // String compare

    @Override
    public int compareTo(Object o) {
        if( o == null ){ return -1; }
        if( o == this ){ return 0; }
        if( o.getClass() != this.getClass() ){ return -1; }
        return ( this.description.compareTo( ( (Article) o ).description) );
    }

    public String toString (){
        return numero + " : " + description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, description);
    }

    //@Override
    //public int compare(Object o1, Object o2) {
    //    return ((Article)o1).numero - ((Article)o2).numero;
    //}

    @Override
    public int compare(Object o1, Object o2) {
        return ((Article)o1).description.compareTo(((Article)o2).description);
    }
}
