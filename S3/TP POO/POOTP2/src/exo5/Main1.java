package exo5;

public class Main1 {
    public static void main(String[] args){
        String[] alphabet = {"a","b","c","d","e"};
        Pile pile = new Pile();

        for( int i = 0 ; i < alphabet.length ; i++) {
            pile.empiler(alphabet[i]);
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
