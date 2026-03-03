package fr.miage.orleans.mac;

public class Main {
    public static void main(String[] args) throws AnimalDoesNotExistException {
        AnimalFactory animalFactory = new AnimalFactory();

        Animal c1 = animalFactory.makeAnimal("chat");
        Animal c2 = animalFactory.makeAnimal("chat");

        System.out.println(c1 == c2);
        System.out.println(c1.equals(c2));
    }
}
