package exo5;

public class Main {
    public static void main(String[] args){
        int a = 4;
        int b = 4;
        int c = 7;
        //vérifie si triangle | si oui rentrer dans le "if"
        if( ( c < a + b ) && ( b < c + a ) && ( a < b + c ) ){

            boolean isocele = false;
            boolean rectangle = false;

            if( a == b && a ==c ){
                System.out.println("triangle equilateral");
            } else {
                if( a == b || a == c || b == c ){
                    isocele = true;
                }
                if( ( a*a == b*b + c*c ) || ( b*b == a*a + c*c ) || ( c*c == b*b + a*a ) ){
                    rectangle = true;
                }
                if( isocele && rectangle ) {
                    System.out.println("triangle isocele rectangle");
                } else if (rectangle && !isocele) {
                    System.out.println("triangle rectangle");
                } else if ( isocele && !rectangle ){
                    System.out.println("triangle isocele");
                } else {
                    System.out.println("triangle quelconque");
                }
            }
        }
    }
}
