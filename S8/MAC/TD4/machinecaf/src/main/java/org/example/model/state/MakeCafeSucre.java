package org.example.model.state;

import org.example.model.Actions;
import org.example.model.MachineCafe;

public class MakeCafeSucre extends AbstractState {

    public MakeCafeSucre(MachineCafe machineCafe) {
        super(machineCafe);
    }

    @Override
    public State use(Actions a) throws UnhandledOperationException {
        return switch (a) {
            case PICKUP_CAFE -> {
                System.out.println("Café sucré récupéré");
                if (getMachineCafe().getCafeRestants() == 0) {
                    yield new WaitingRecharger(getMachineCafe());
                }
                yield new WaitingToken(getMachineCafe());
            }
            default -> throw new UnhandledOperationException();
        };
    }
}
