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
        operations = spy(this.getInstance());
    }

    protected abstract Operations getInstance();
}
