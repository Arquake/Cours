package exo6;

public class Main {
    public static void main(String[] args){
        boolean[] crible = new boolean[1001];
        crible[0] = false;
        crible[1] = false;
        crible[2] = true;
        boolean estPremier = true;
        for( int i = 3 ; i < crible.length ; i = i + 2 ) {
            for( int j = i - 2 ; j > 1 ; j = j - 2 ){
                if( i % j == 0 ){
                    estPremier = false;
                    break;
                }
            }
            if(estPremier){
                crible[i]=true;
            } else {
                crible[i] = false;
            }
            estPremier = true;
        }
        System.out.println("2 est premier");
        for( int i = 3 ; i < crible.length ; i = i + 2){
            if( crible[i] ){
                System.out.println(i + " est premier");
            }
        }
    }
}
