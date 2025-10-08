package modele.operations;

import modele.exceptions.NonSupporteeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SoustractionTest extends OperationsAbstractTest{

    @Override
    protected Operations getInstance() {
        return new Soustraction(null);
    }

    @Test
    void resValid() {
        Assertions.assertEquals(0d, operations.getResultat(3d,3d));
    }
}