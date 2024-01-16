package exo1;

public class Main {
    public static void main(String[] args){
        args = new String[]{"1", "Test", "5.5", "3"};
        long[] entiers = new long[args.length];

            for (int i = 0; i < entiers.length; i++) {
                try {
                entiers[i] = Integer.parseInt(args[i]);
                } catch (Exception e){
                    System.out.println("non integer passed through parameters has been ignored");
                }
            }

        long added = 0;
        for(int i = 0 ; i < entiers.length ; i++ ){
            added += entiers[i];
        }
        System.out.println(added);
    }
}
