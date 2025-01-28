import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int n = 27;
        for( int i = 2 ; i<=27 ; i++){
            if (n % i == 0) {
                System.out.println(i + " est le plus petit diviseur");
                break;
            }
        }
    }
}