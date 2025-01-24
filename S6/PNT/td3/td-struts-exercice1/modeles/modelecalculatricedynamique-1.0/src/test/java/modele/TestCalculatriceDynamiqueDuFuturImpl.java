package modele;

import modele.exceptions.NonSupporteeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCalculatriceDynamiqueDuFuturImpl {

    CalculatriceDynamiqueDuFutur calculatriceDynamiqueDuFutur;

    @Before
    public void initialisation() {
        this.calculatriceDynamiqueDuFutur = new CalculatriceDynamiqueDuFuturImpl();
    }


    @Test
    public void testOperationSommes() throws NonSupporteeException {

        Assert.assertTrue(calculatriceDynamiqueDuFutur.doCalcul("Somme",1,2)==3);
        Assert.assertTrue(calculatriceDynamiqueDuFutur.getValeurCompteur()==1);

    }


    @Test
    public void testOperationDivision() throws NonSupporteeException {

        Assert.assertEquals(calculatriceDynamiqueDuFutur.doCalcul("Division",1,2),0.5,0);
        Assert.assertTrue(calculatriceDynamiqueDuFutur.getValeurCompteur()==1);

    }


    @Test(expected = NonSupporteeException.class)
    public void testOperationModule() throws NonSupporteeException {
        calculatriceDynamiqueDuFutur.doCalcul("Module",1,2);
    }




}
