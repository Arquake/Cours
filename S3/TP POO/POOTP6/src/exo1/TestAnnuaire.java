package exo1;

public class TestAnnuaire {
    public static void main(String[] args) {
        Annuaire annu = new Annuaire();
        Personne per1 = new Personne( "D", "D");
        Personne per2 = new Personne( "A", "A");
        Personne per3 = new Personne( "G", "G");

        NumTel n1 = new NumTel(1, 'T');
        NumTel n2 = new NumTel(2);
        NumTel n3 = new NumTel(3, 'D');
        NumTel n4 = new NumTel(4);
        NumTel n5 = new NumTel(5);
        NumTel n6 = new NumTel(6);

        ListeNumTel ln1 = new ListeNumTel(n1);
        ListeNumTel ln2 = new ListeNumTel(n2);
        ListeNumTel ln3 = new ListeNumTel(n4);

        ln2.ajouterFin(n3);
        ln3.ajouterFin(n5);

        annu.ajouterEntree(per1, ln1);
        annu.ajouterEntree(per2, ln2);
        annu.ajouterEntree(per3, ln3);

        annu.ajouterNumeroFin( per3, n6 );

        System.out.println( annu.toString() );
    }
}
