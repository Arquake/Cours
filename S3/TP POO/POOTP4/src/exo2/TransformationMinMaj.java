package exo2;

import java.util.Scanner;

public class TransformationMinMaj {
    public static void main(String[] args) {
        String[] lesMots = {"Bonjour", "HIER","Aujourd'hui"};
        Scanner scan = new Scanner(System.in);
        String choice ="";
        while(choice.compareTo((String)"a")!=0 && choice.compareTo((String)"i")!=0){
            System.out.print("input (a/i) : ");
            choice = scan.nextLine();
            System.out.println();
        }
        if ( choice.compareTo((String)"a")==0){
            for(int i = 0 ; i < lesMots.length ; i++ ){
                System.out.println(lesMots[i].toUpperCase());
            }
        } else {
            for(int i = 0 ; i < lesMots.length ; i++ ){
                System.out.println(lesMots[i].toLowerCase());
            }
        }
    }
}
