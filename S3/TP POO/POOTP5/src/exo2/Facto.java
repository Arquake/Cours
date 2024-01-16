package exo2;

public class Facto {

    private static long factRec (int n){
        assert n < 0  : "Negative number passe through parameters";
        long sum = 1;
        for ( int i = 2 ; i <= n ; i++ ){
            sum *= i;
        }
        return sum;
    }

    /**
     * factorielle method return the factorial of the parameter n of type int
     * @param n
     * @return sum
     * @throws RuntimeException
     *
     *
     */
    public static long factorielle (int n) throws RuntimeException{
        if ( n < 0 ) { throw new RuntimeException("negative number passed as parameter in factorial method") ; }
        if( n > 20 ) { throw new RuntimeException("number too big") ; }
        long sum = 1;
        for ( int i = 2 ; i <= n ; i++ ){
            sum *= i;
        }

        return sum;
    }
}
