package modele.operations;

import modele.exceptions.NonSupporteeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OperationsTest extends OperationsAbstractTest {

    @Override
    protected Operations getInstance() {
        return spy(Operations.class);
    }

    @Test
    void validResult() throws NonSupporteeException {
        doReturn(6d).when(operations).getResultat("+",3d,3d);

        Assertions.assertDoesNotThrow(()->operations.getResultat("+",3d,3d));
    }

    @Test
    void resultValidValue() throws NonSupporteeException {
        doReturn(6d).when(operations).getResultat("+",3d,3d);

        Assertions.assertEquals(6d,operations.getResultat("+",3d,3d));
    }

    @Test
    void resultHasToUseNextSign() throws NonSupporteeException {
        doReturn(6d).when(operations).getResultat("-",3d,3d);

        Assertions.assertEquals(6d,operations.getResultat("-",3d,3d));
    }

    @Test
    void resultInvalidOperand() throws NonSupporteeException {
        doThrow(NonSupporteeException.class).when(operations).getResultat("-",3d,3d);

        Assertions.assertThrows(NonSupporteeException.class,()->operations.getResultat("-",3d,3d));
    }

    @Test
    void validResultWithAssignedSigns() {
        doReturn(6d).when(operations).getResultat(3d,3d);

        Assertions.assertDoesNotThrow(()->operations.getResultat(3d,3d));
    }

    @Test
    void resultWithAssignedSigns() {
        doReturn(6d).when(operations).getResultat(3d,3d);

        Assertions.assertEquals(6d,operations.getResultat(3d,3d));
    }

    @Test
    void operationsNoNextSign() {
        doReturn(null).when(operations).getOperations();

        Assertions.assertNull(operations.getOperations());
    }

    @Test
    void operationsNextSign() {
        List<String> returnedExpectedArray = new ArrayList<>();
        returnedExpectedArray.add("+"); // next
        returnedExpectedArray.add("-"); // current

        doReturn(returnedExpectedArray).when(operations).getOperations();

        Assertions.assertEquals(returnedExpectedArray,operations.getOperations());
    }

    @Test
    void operationNoNext() {
        List<String> returnedExpectedArray = new ArrayList<>();
        returnedExpectedArray.add("+"); // current

        doReturn(returnedExpectedArray).when(operations).getOperations();

        Assertions.assertEquals(returnedExpectedArray,operations.getOperations());
    }

    @Test
    void noOperationNoNextSign() {
        List<String> returnedExpectedArray = new ArrayList<>();
        returnedExpectedArray.add(null); // current

        doReturn(returnedExpectedArray).when(operations).getOperations();

        Assertions.assertEquals(returnedExpectedArray,operations.getOperations());
    }

    @Test
    void noOperationNextSign() {
        List<String> returnedExpectedArray = new ArrayList<>();
        returnedExpectedArray.add("-"); // next
        returnedExpectedArray.add(null); // current


        doReturn(returnedExpectedArray).when(operations).getOperations();

        Assertions.assertEquals(returnedExpectedArray,operations.getOperations());
    }
}