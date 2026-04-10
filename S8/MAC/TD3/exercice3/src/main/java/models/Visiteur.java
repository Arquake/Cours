package models;

import models.operands.Diviser;
import models.operands.Moins;
import models.operands.Multiplier;
import models.operands.Plus;

public class Visiteur implements IVisiteur{

    int res;

    public Visiteur() {
        this.res = 0;
    }

    @Override
    public int getValue() {
        return res;
    }

    @Override
    public void visiterMoins(Moins m) {
        m.getLeft().accept(this);
        int rl = res;
        m.getRight().accept(this);
        int rr = res;
        res = rl - rr;
    }

    @Override
    public void visiterPlus(Plus p) {
        p.getLeft().accept(this);
        int rl = res;
        p.getRight().accept(this);
        int rr = res;
        res = rl + rr;
    }

    @Override
    public void visiterMultiplier(Multiplier m) {
        m.getLeft().accept(this);
        int rl = res;
        m.getRight().accept(this);
        int rr = res;
        res = rl * rr;
    }

    @Override
    public void visiterDiviser(Diviser d) {
        d.getLeft().accept(this);
        int rl = res;
        d.getRight().accept(this);
        int rr = res;
        res = rl / rr;
    }

    @Override
    public void visiterDigit(Digit d) {
        res = d.getValue();
    }
}
