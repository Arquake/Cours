package fr.miage.orleans.mac;

public class Chapeau implements IPersonne {

    IPersonne personne;

    public Chapeau(IPersonne personne) {
        this.personne = personne;
    }

    public void sortir() {
        System.out.println("Découvre l'extérieur et va toucher de l'herbe");
    }

    @Override
    public void respirer() {
        System.out.println("Respire difficilement");
    }

    @Override
    public void manger() {
        System.out.println("Mange difficilement");
    }
}
