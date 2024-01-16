package exo5;
import java.util.Random;

public class Main2 {
    public static void main(String[] args){
        Random rand = new Random();
        Pile pile = new Pile();

        for( int i = 0 ; i < 5 ; i++) {
            pile.empiler(String.valueOf(rand.nextInt(1,101)));
        }

        pile.consulterContenu();

        pile.depiler();
        pile.depiler();

        pile.consulterContenu();

    }

    private static void switcher(Pile pile1, Pile pile2){
        String tempMaillon;
        while( !pile1.estVide() ){
            tempMaillon = pile1.depiler();
            pile2.empiler(tempMaillon);
        }
    }

    private static void pileShower(Pile pile){
        if(!pile.estVide()) {
            Maillon tempMaillon = pile.getSommet();
            System.out.println(tempMaillon.getValeur());
            while (tempMaillon.getSuivant()!=null){
                tempMaillon = tempMaillon.getSuivant();
                System.out.println(tempMaillon.getValeur());
            }
            System.out.println();
        }
    }
}
