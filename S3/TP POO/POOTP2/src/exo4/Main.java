package exo4;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Entrer un entier");

        int n = myObj.nextInt();

        while (n >= 0 ){
            System.out.println(n--);
        }

    }
}
