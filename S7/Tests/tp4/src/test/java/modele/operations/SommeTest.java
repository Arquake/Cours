package modele.operations;

import modele.exceptions.NonSupporteeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SommeTest extends OperationsAbstractTest {

    @Override
    protected Operations getInstance() {
        return new Somme(null);
    }

    @Test
    void resValid() {
        Assertions.assertEquals(6d, operations.getResultat(3d,3d));
    }
}