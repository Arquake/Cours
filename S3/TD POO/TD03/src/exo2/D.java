package exo2;

public class D extends C {
    private String vD;
    public D() {
        this("-4");
    }
    public D(String vD) {
        super(Integer.parseInt(vD));
        this.vD=vD;
    }
    public D(long vA, String vD) {
        super(vA,
                Integer.parseInt(vD));
        this.vD=vD;
    }
    public String getvD(){
        return vD;
    }
    public double somme (A a){
        if ( a instanceof D)
            return
                    getvC()*((C)a).getvC();
        return super.somme(a);
    }
    public String toString(){
        return "D<" +super.toString()
                + ">(\"" + vD + "\")";
    }
}
