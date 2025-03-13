package modele;

public class Cheval {

    private long id;
    private String nomCheval;
    private int age;

    private static long ID = 0;

    public Cheval() {
    }

    public Cheval(String nomCheval, int age) {
        this.id = ID++;
        this.nomCheval = nomCheval;
        this.age = age;
    }

    public long getId() {
        return id;
    }


    public String getNomCheval() {
        return nomCheval;
    }

    public void setNomCheval(String nomCheval) {
        this.nomCheval = nomCheval;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
