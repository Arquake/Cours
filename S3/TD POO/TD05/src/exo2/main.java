package exo2;

public class main {
    public static void main(String[] args) {
        try {
            Horaire h = new Horaire(24, 60);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
