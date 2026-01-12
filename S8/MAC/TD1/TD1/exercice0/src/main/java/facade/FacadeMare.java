package facade;

import mare.Mare;
import mare.Poisson;

import java.util.Collection;

public class FacadeMare {

    /**
     * La mare gérée par la façade
     */
    private Mare maMare;


    /**
     * L'animation de la mare est lancée dans un thread
     */
    private Thread activiteMare;

    public FacadeMare() {
    }

    /**
     * On crée une mare selon les dimensions données et avec un nombre de poissons donné.
     * Les poissons sont placés aléatoirement dans la mare
     * @param dimXMare
     * @param dimYMare
     * @param nbPoissons
     */
    public void creerMare(int dimXMare, int dimYMare, int nbPoissons) {
        this.maMare = new Mare(dimXMare,dimYMare);
        for (int i =0;i<nbPoissons;i++) {
            int posx=(int)(dimXMare*Math.random());
            int posy=(int)(dimYMare*Math.random());
            /**
             * Cela peut sembler bizarre comme instruction, mais lors de sa création, le poisson s'inscrit dans la mare.
             */
            new Poisson(posx,posy,maMare);
        }
        this.activiteMare = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                maMare.majPositionPoissons();
            }
        });
    }

    /**
     * Permet de lancer l'animation de la mare ou de la relancer
     */

    public void animerMare() {
        Thread.State state = activiteMare.getState();
        if (state == Thread.State.TERMINATED) {
            this.activiteMare = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    maMare.majPositionPoissons();
                }
            });
        }
        activiteMare.start();
    }

    /**
     * Permet de stopper l'animation de la mare
     * @throws InterruptedException
     */

    public void gelerMare() throws InterruptedException {
        this.activiteMare.interrupt();
        this.activiteMare.join();


    }


    public Collection<Poisson> getMaMare() {
        return maMare.getPoissons();
    }
}
