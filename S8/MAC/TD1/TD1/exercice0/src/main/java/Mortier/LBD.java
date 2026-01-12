package Mortier;

public class LBD {
    int rayon = 2;
    PointImpact p;

    public LBD() {
    }

    public LBD(PointImpact p) {
        this.p = p;
    }

    public PointImpact getP() {
        return p;
    }

    public void setP(PointImpact p) {
        this.p = p;
    }

    public int getRayon() {
        return rayon;
    }
}
