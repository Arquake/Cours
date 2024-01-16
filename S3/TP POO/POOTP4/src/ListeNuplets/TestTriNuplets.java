package ListeNuplets;
import java.util.Arrays;
import java.util.Scanner;

public class TestTriNuplets {
    public static void main(String[] args) {
        int[] i = creationListe();
        ListeNuplets list = new ListeNuplets(i);
        System.out.println(list.toString());
        list.trier();
        System.out.println(list.toString());
        ListeNuplets.Nuplets nup = list.getNuplet(0);
        System.out.println(nup.nbElement());
        System.out.println(nup.toString());

    }

    private static int[] creationListe(){
        Scanner scan = new Scanner(System.in);
        int[] content = new int[1];
        int[] newcontent;
        int i = 0;
        String lineToRead;
        while( true ){
            System.out.print("input (\"B\" to break) : ");
            lineToRead = scan.nextLine();
            System.out.println();
            if( ((String)"B").compareTo( lineToRead ) == 0 ){
                break;
            }
            try{
                if( Integer.parseInt(lineToRead) == Double.parseDouble(lineToRead) && Integer.parseInt(lineToRead) >= 0){
                    if ( i != 0 ){
                        newcontent = content;
                        content = new int[content.length + 1 ];
                        for( int j = 0; j < newcontent.length; j++){
                            content[j]=newcontent[j];
                        }
                    }
                    content[content.length - 1] = Integer.parseInt(lineToRead);
                    i++;
                } else {
                    System.out.println("Input is not a positive integer");
                }

            } catch(Exception e) {
                System.out.println("Invalid Input");
            }
        }
        return content;
    }
}
