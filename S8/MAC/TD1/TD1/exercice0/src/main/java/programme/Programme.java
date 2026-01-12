package programme;

import Mortier.LBD;
import Mortier.PointImpact;
import facade.FacadeMare;
import mare.Mare;
import mare.Poisson;

import java.util.Scanner;

import static java.lang.Thread.sleep;


public class Programme {

    public static void main(String[] args) throws InterruptedException {


        FacadeMare facadeMare = new FacadeMare();
        facadeMare.creerMare(40,50,3);
        facadeMare.animerMare();
        sleep(1500);
        facadeMare.gelerMare();
        System.out.println("La mare\n---------------------");
        facadeMare.getMaMare().stream().forEach(e -> System.out.println(e));
        facadeMare.animerMare();
        sleep(1500);
        facadeMare.gelerMare();

        System.out.println("La mare\n---------------------");
        facadeMare.getMaMare().stream().forEach(e -> System.out.println(e));

        while (true) {
            try {
                Scanner myObj = new Scanner(System.in);
                System.out.println("Où voulez vous tirer au LBD ? (format: 'x,y')");

                String coordinates = myObj.nextLine();
                String[] coords = coordinates.replace(" ","").split(",");

                facadeMare.declencherLBD(new LBD(new PointImpact(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]))));

                facadeMare.animerMare();
                sleep(1500);
                facadeMare.gelerMare();
                System.out.println("La mare\n---------------------");
                facadeMare.getMaMare().stream().forEach(e -> System.out.println(e));
            }
            catch (Exception e) {
                System.out.println("Frer en christ fais un effort");
            }
        }
    }
}
