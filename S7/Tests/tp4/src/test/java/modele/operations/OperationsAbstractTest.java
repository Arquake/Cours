package modele.operations;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Spy;

import static org.mockito.Mockito.spy;

public abstract class OperationsAbstractTest {

    @Spy
    Operations operations;

    @Spy
    Operations nextOperation;

    @BeforeEach
    void init() {
        operations = spy(Operations.class);
    }

    protected abstract Operations getInstance();
}
