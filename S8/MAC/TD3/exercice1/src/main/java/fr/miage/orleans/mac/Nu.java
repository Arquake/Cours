package fr.miage.orleans.mac;

public class Nu implements IPersonne {

    public Nu() {}

    @Override
    public void respirer() {
        System.out.println("Respire aisément");
    }

    @Override
    public void manger() {
        System.out.println("Mange aisément");
    }
}
