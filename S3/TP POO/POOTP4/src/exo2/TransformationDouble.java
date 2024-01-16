package exo2;


public class TransformationDouble {
    public static void main(String[] args) {
        Object[] list = {3.2d,6.6d,4.4d};
        EnsTransformable objet = new EnsTransformable(list );
        for (int i = 0 ; i < list.length ; i++){
            list[i] = Math.sqrt((double)list[i]);
        }
        printList(list);
        for (int i = 0 ; i < list.length ; i++){
            list[i] = Math.pow((double)list[i], 2);
        }
        printList(list);
        for (int i = 0 ; i < list.length ; i++){
            list[i] = Math.pow((double)list[i], 3);
        }
        printList(list);
    }

    static private void printList(Object[] list){
        System.out.print("(");
        for (int i = 0 ; i < list.length-1 ; i++){
            System.out.print(list[i]+",");
        }
        System.out.println(list[list.length-1]+")");
    }
}
