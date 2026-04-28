package org.example.model.state;

import org.example.model.Actions;
import org.example.model.MachineCafe;

@FunctionalInterface
public interface State {

    State use(Actions a) throws UnhandledOperationException;
}
