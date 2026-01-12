package mare;


public class Poisson {

    /**
     * Mare dans laquelle est censé être le poisson
     */
    private Mare mare;
    /**
     * Position en x du poisson
     */
    private int x;
    /**
     * Position en y du poisson
     */
    private int y;
    /**
     * si estMort= true, Le poisson est mort. Cela arrive quand on place un poisson en dehors de la mare
     */
    private boolean estAssomme;

    /**
     * Sens de déplacement du poisson
     */
    private int sensDeplacement;

    public Poisson(int x, int y, Mare mare) {
        this.x = x;
        this.y = y;
        this.estAssomme = false;
        this.sensDeplacement = 1;
        try {
            mare.ajouterPoisson(this);
        } catch (PoissonOutOfBoundException e) {
            this.estAssomme = true;
        }
        this.mare = mare;
    }


    public void setMare(Mare mare) {
        this.mare = mare;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Permet de faire un déplacement du poisson. Si le poisson arrive à une extrémité, il fait demi-tour.
     */

    public void deplacer() {
        if (!this.estAssomme) {
            if (this.x == 0 || this.x == this.mare.getDimX()) {
                this.sensDeplacement *= -1;
            }
            this.x = this.x + this.sensDeplacement;
        }
    }

    @Override
    public String toString() {
        return "Poisson{" +
                "x=" + x +
                ", y=" + y +
                ", sensDeplacement=" + sensDeplacement +
                '}';
    }
}
