package org.example.model.state;

import org.example.model.Actions;
import org.example.model.MachineCafe;

public class WaitingCommand extends AbstractState {

    public WaitingCommand(MachineCafe machineCafe) {
        super(machineCafe);
    }

    public State use(Actions a) throws UnhandledOperationException {
        return switch (a) {
            case ANNULATION -> {
                System.out.println("Annulation de la commande");
                yield new WaitingToken(getMachineCafe());
            }
            case BUTTON_NO_SUCRE -> {
                try {
                    getMachineCafe().removeOneCoffee();
                    System.out.println("Café pas sucré fait");
                    yield new MakeCafe(getMachineCafe());
                } catch (NotEnoughCafeException e) {
                    System.out.println(e.getMessage());
                    yield this;
                }
            }
            case BUTTON_SUCRE -> {
                try {
                    getMachineCafe().removeOneCoffee();
                    System.out.println("Café sucré fait");
                    yield new MakeCafeSucre(getMachineCafe());
                } catch (NotEnoughCafeException e) {
                    System.out.println(e.getMessage());
                    yield this;
                }
            }
            default -> throw new UnhandledOperationException();
        };
    }
}
