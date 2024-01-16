package exo2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;
        while( true ){
            try {
                System.out.print("Enter a number ( or press \"q\" to quit ) : ");
                line = sc.nextLine();
                int n;
                if (line.compareTo((String) "q") == 0) {
                    break;
                }
                n = Integer.parseInt(line);
                if ( n > 20) {
                    System.out.println("number too big ");
                } else if ( n < 0 ){
                    System.out.println("negative number ");
                } else {
                        System.out.println(Facto.factorielle(Integer.parseInt(line)));
                }

            } catch (Exception e) {
                System.out.println("Invalid Input : " + e );
            }
        }
    }
}
