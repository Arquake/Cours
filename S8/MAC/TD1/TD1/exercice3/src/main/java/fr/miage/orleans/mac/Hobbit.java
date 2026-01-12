package fr.miage.orleans.mac;

public class Hobbit {

    private String name;

    public Hobbit() {
    }

    public Hobbit(String name) {
        this.name = name;
    }

    public void mange(Fruit f) {
        System.out.println(name + " mange: " + f.toString());
    }
}
