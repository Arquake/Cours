package mystere;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutilsListeTest {

    @Test
    public void testVide() {
        List<Integer> l = new ArrayList<>();
        OutilsListe instance = new OutilsListe(l);
        assert (!instance.estElementDe(5));
    }

    @Test
    public void testNormal() {
        List<Integer> l = new ArrayList<>();
        l.add(5);
        l.add(6);
        l.add(7);
        l.add(8);

        OutilsListe instance = new OutilsListe(l);
        assert(instance.estElementDe(7));
    }

    @Test
    public void elementManquant() {
        List<Integer> l = new ArrayList<>();
        l.add(5);
        l.add(6);
        l.add(7);
        l.add(8);

        OutilsListe instance = new OutilsListe(l);
        assert (!instance.estElementDe(10));
    }

    @Test
    public void desordreList() {
        List<Integer> l = new ArrayList<>();
        l.add(8);
        l.add(6);
        l.add(7);
        l.add(8);

        OutilsListe instance = new OutilsListe(l);
        assert (instance.estElementDe(6));
    }

}