package org.example;

import org.example.model.Actions;
import org.example.model.MachineCafe;
import org.example.model.state.UnhandledOperationException;
import org.example.model.state.WaitingToken;

public class Main {
    public static void main(String[] args) {
        MachineCafe machine = new MachineCafe(WaitingToken::new);

        Actions[] listActions = {Actions.INSERT_JETON, Actions.INSERT_JETON, Actions.BUTTON_NO_SUCRE, Actions.PICKUP_CAFE, Actions.INSERT_JETON, Actions.BUTTON_SUCRE, Actions.PICKUP_CAFE};

        for (Actions a: listActions) {
            try {
                machine.use(a);
            } catch (UnhandledOperationException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}