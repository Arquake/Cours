package ecologie;

public class SimulateurEcoSystemes {
    public static void main(String[] args) {
        // Simulation de l'écosystème africain
        IHerbivore zebre = () -> System.out.println("Zèbre broutant l'herbe de la savane");
        ICarnivore lion = () -> System.out.println("ecologiecorrection.Lion chassant des proies dans la savane");
        EcoSysteme eco = new EcoSysteme("Écosystème Africain", zebre, lion);

        System.out.println("Écosystème Africain :");
        zebre.manger();
        lion.chasser();

        // Simulation de l'écosystème arctique
        IHerbivore renne = ()-> System.out.println("ecologiecorrection.Renne mangeant du lichen arctique");
        ICarnivore ours = () -> System.out.println("ecologiecorrection.Ours polaire chassant des phoques sur la glace");
        EcoSysteme eco = new EcoSysteme("Écosystème Arctique", renne, ours);

        System.out.println("\nÉcosystème Arctique :");
        renne.manger();
        ours.chasser();
    }
}

