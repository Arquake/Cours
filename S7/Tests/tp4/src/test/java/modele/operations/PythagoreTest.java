package modele.operations;

import modele.exceptions.NonSupporteeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PythagoreTest extends OperationsAbstractTest {

    @Override
    protected Operations getInstance() {
        return new Pythagore(null);
    }

    @Test
    void resValid() {
        Assertions.assertEquals(Math.sqrt(18d), operations.getResultat(3d,3d));
    }
}