package exo4;

public class Main {
    public static void main(String[] args) {
        Point p1= new Point(4,6);
        System.out.println("num : "+p1.getNum());
        Point p0= new Point();
        System.out.println("distance :"+p1.distance(p0));
        System.out.println("distance :"+p1.distance());
    }

}
