package fr.miage.orleans.mac;

public class Main {
    public static void main(String[] args) {
        Chat c1 = AnimalFactory.makeCat();
        Chat c2 = AnimalFactory.makeCat();

        System.out.println(c1 == c2);
    }
}
