package org.example.model.state;

import org.example.model.Actions;
import org.example.model.MachineCafe;

public class MakeCafe extends AbstractState {

    public MakeCafe(MachineCafe machineCafe) {
        super(machineCafe);
    }

    @Override
    public State use(Actions a) throws UnhandledOperationException {
        return switch (a) {
            case PICKUP_CAFE -> {
                System.out.println("Café récupéré");
                yield new WaitingToken(getMachineCafe());
            }
            default -> throw new UnhandledOperationException();
        };
    }
}
