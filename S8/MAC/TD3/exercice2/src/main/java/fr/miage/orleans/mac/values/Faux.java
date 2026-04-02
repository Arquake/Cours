package fr.miage.orleans.mac.values;

import fr.miage.orleans.mac.CustomBooleans;
import fr.miage.orleans.mac.operation.Operation;

public class Faux implements Operation {
    @Override
    public CustomBooleans evaluer() {
        return CustomBooleans.FAUX;
    }
}
