package org.example.model;

import org.example.model.state.NotEnoughCafeException;
import org.example.model.state.State;
import org.example.model.state.UnhandledOperationException;

import java.util.function.Function;

public class MachineCafe {

    private State currentState;
    private short cafeRestants;


    public MachineCafe(Function<MachineCafe, State> baseStateMaker) {
        this.currentState = baseStateMaker.apply(this);
        cafeRestants = 1;
    }

    public void use(Actions action) throws UnhandledOperationException {
        currentState = currentState.use(action);
    }

    public short getCafeRestants() {
        return cafeRestants;
    }

    public void removeOneCoffee() throws NotEnoughCafeException {
        if (cafeRestants != 0) {
            cafeRestants--;
        } else {
            throw new NotEnoughCafeException();
        }
    }

    public void restock(short cafe) {
        cafeRestants += cafe;
    }
}
