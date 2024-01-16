package ListeNuplets;


import java.util.Arrays;
import java.util.Scanner;

public class ListeNuplets {
    private Nuplets[] lesNuplets;

    static class Nuplets {
        private int[] content;

        public Nuplets(int size) {
            Scanner scan = new Scanner(System.in);
            this.content = new int[size];
            int i = 0;
            String lineToRead;
            while( i < size ){
                System.out.print("input : ");
                lineToRead = scan.nextLine();
                System.out.println();
                try{
                    if( Integer.parseInt(lineToRead) == Double.parseDouble(lineToRead) && Integer.parseInt(lineToRead) >= 0){
                        content[i] = Integer.parseInt(lineToRead);
                        i++;
                    } else {
                        System.out.println("Input is not a positive integer");
                    }

                } catch(Exception e) {
                    System.out.println("Invalid Input");
                }
            }
            Arrays.sort(content);
        }

        @Override
        public String toString() {
            String chain = "(";
            for ( int i = 0 ; i < content.length - 1 ; i++ ){
                chain += content[i] + ",";
            }
            chain += content[content.length - 1] + ")";
            return chain;
        }

        public int nbElement(){
            return content.length;
        }

        public int getElement(int index){
            if ( 0 <= index && index < content.length ){
                return content[index];
            }
            return -1;
        }
    }

    public ListeNuplets(int... lesTailles) {
        this.lesNuplets = new Nuplets[lesTailles.length];
        for( int i = 0 ; i < lesTailles.length ; i++ ){
            lesNuplets[i] = new Nuplets(lesTailles[i]);
        }
    }

    @Override
    public String toString() {
        return "ListeNuplets{" +
                "lesNuplets=" + Arrays.toString(lesNuplets) +
                '}';
    }

    public Nuplets getNuplet(int index){
        if (0 <= index && index < lesNuplets.length ){
            return lesNuplets[index];
        }
        return null;
    }

    public void trier(){
        boolean hasBroke = false;
        Nuplets transition;
        for( int i = 0 ; i < lesNuplets.length - 1 ; i++ ){
            for( int j = i + 1 ; j < lesNuplets.length ; j++){
                for( int k = 0 ; k < (lesNuplets[i].nbElement()<lesNuplets[j].nbElement()? lesNuplets[i].nbElement() : lesNuplets[j].nbElement()) ; k++ ){
                    if( lesNuplets[i].getElement(k) > lesNuplets[j].getElement(k) ){
                        transition = lesNuplets[i];
                        lesNuplets[i] = lesNuplets[j];
                        lesNuplets[j]=transition;
                        hasBroke=true;
                        break;
                    }
                }
                if( !hasBroke ){
                    if( lesNuplets[j].nbElement() < lesNuplets[i].nbElement() ){
                        transition = lesNuplets[i];
                        lesNuplets[i] = lesNuplets[j];
                        lesNuplets[j]=transition;
                    }
                }
                hasBroke=false;
            }
        }
    }
}
