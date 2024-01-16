package exo3;

public class Main {
    public static long main(String[] args){
        long[] entiers = new long[args.length];
        try {
            for (int i = 0; i < entiers.length; i++) {
                entiers[i] = Integer.parseInt(args[i]);
            }
        } catch (Exception e){
            System.out.println("non integer passed through parameters");
            System.out.println(e);
            return -1;
        }
        long added = 0;
        for(int i = 0 ; i < entiers.length ; i++ ){
            added += entiers[i];
        }
        return (added);
    }
}
