package fr.miage.orleans.mac.programme;

import fr.miage.orleans.mac.Mortier.LBD;
import fr.miage.orleans.mac.Mortier.PointImpact;
import fr.miage.orleans.mac.exceptions.QuitException;
import fr.miage.orleans.mac.facade.FacadeMare;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.Thread.sleep;


public class Programme {

    static FacadeMare facadeMare;

    public static void main(String[] args) throws InterruptedException {


        facadeMare = new FacadeMare();
        facadeMare.creerMare(40,50,3);
        animateAndDisplayMare();

        while (true) {
            try {
                PointImpact p = getPointImpact();

                facadeMare.declencherLBD(new LBD(p));

                displayAndAnimateMare();
            }
            catch (QuitException e) {
                return;
            }
        }
    }

    private static void displayMare() {
        System.out.println("La mare\n---------------------");
        facadeMare.getMaMare().stream().forEach(e -> System.out.println(e));
    }

    private static void animateMare() throws InterruptedException {
        facadeMare.animerMare();
        sleep(1500);
        facadeMare.gelerMare();
    }

    private static void displayAndAnimateMare() throws InterruptedException {
        displayMare();
        animateMare();
    }

    private static void animateAndDisplayMare() throws InterruptedException {
        animateMare();
        displayMare();
    }

    private static PointImpact getPointImpact() throws QuitException {
        while (true) {
            try {
                Scanner myObj = new Scanner(System.in);
                System.out.println("Où voulez vous tirer au LBD ('q' pour quitter) ? (format: 'x,y')");

                String coordinates = myObj.nextLine();
                if (coordinates.equals("q")) throw new QuitException();
                String[] coords = coordinates.replace(" ","").split(",");

                return new PointImpact(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
            }
            catch (NoSuchElementException | IllegalStateException | NumberFormatException e) {
                System.out.println("Frer en christ fais un effort");
            }
        }
    }
}
