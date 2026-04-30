package org.example.model.state;

import org.example.model.Actions;
import org.example.model.MachineCafe;

public class WaitingRecharger extends AbstractState {

    public WaitingRecharger(MachineCafe machineCafe) {
        super(machineCafe);
    }
    @Override
    public State use(Actions a) throws UnhandledOperationException {
        return switch (a) {
            case RECHARGER -> {
                System.out.println("Machine rechargé");
                getMachineCafe().restock((short) 10);
                yield new WaitingToken(getMachineCafe());
            }
            default -> throw new UnhandledOperationException();
        };
    }
}
