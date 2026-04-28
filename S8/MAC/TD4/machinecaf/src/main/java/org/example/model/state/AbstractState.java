package org.example.model.state;

import org.example.model.MachineCafe;

public abstract class AbstractState implements State {

    private MachineCafe machineCafe;

    AbstractState(MachineCafe machineCafe) {
        this.machineCafe = machineCafe;
    }

    public MachineCafe getMachineCafe() {
        return machineCafe;
    }
}
