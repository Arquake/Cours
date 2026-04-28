package org.example.model.state;

import org.example.model.Actions;
import org.example.model.MachineCafe;

public class WaitingToken extends AbstractState {

    public WaitingToken(MachineCafe machineCafe) {
        super(machineCafe);
    }


    @Override
    public State use(Actions a) throws UnhandledOperationException {
        return switch (a) {
            case INSERT_JETON -> {
                System.out.println("Jeton inséré");
                yield new WaitingCommand(getMachineCafe());
            }
            default -> throw new UnhandledOperationException();
        };
    }
}
