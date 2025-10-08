package modele.operations;

import modele.exceptions.NonSupporteeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MultiplicationTest extends OperationsAbstractTest {

    @Override
    protected Operations getInstance() {
        return new Multiplication(null);
    }

    @Test
    void resValid() {
        Assertions.assertEquals(9d, operations.getResultat(3d,3d));
    }
}