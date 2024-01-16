package exo4;

public class Point {
    private int x,y,num;
    static int nextNum = 1;
    {num=nextNum++;}

    public Point() {
        this.x = 0;
        this.y = 0;
    }
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p){
        int diffx = this.x-p.x;
        int diffy = this.y-p.y;
        return Math.sqrt(diffx*diffx+ diffy*diffy) ;
    }

    public double distance(){
        int diffx = this.x-0;
        int diffy = this.y-0;
        return Math.sqrt(diffx*diffx+ diffy*diffy) ;
    }

    public void translater (int u, int v){
        this.x = u;
        this.y = v;
    }

    public Point symetrie(){
        return (new Point(-this.x,-this.y));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNum() {
        return num;
    }
}