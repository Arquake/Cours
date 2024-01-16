package exo2;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Personnel listePersonnel = new Personnel();
        Commerciaux comer = new Commerciaux("C", LocalDate.now(), 3200);
        Employe emplo = new Employe("E", LocalDate.now(),2000);
        Employe[] listeEmploye = {comer, emplo};
        Responsable respo = new Responsable("R", LocalDate.now(), "responsable", 2, 3624,listeEmploye);
        listePersonnel.ajouterEmploye(comer);
        listePersonnel.ajouterEmploye(emplo);
        listePersonnel.ajouterEmploye(respo);
        System.out.println(listePersonnel.montantSalaireBrutsMensuels());
        System.out.println(listePersonnel.toString());
    }
}

