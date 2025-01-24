package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FacadeModele {



    List<Etudiant> etudiants;


    public FacadeModele() {
        this.etudiants = new ArrayList<Etudiant>();
        this.etudiants.add(new Etudiant("00000001","Babar1","Babar7",156));
        this.etudiants.add(new Etudiant("00000002","Babar2","Babar6",155));
        this.etudiants.add(new Etudiant("00000003","Babar3","Babar5",154));
        this.etudiants.add(new Etudiant("00000004","Babar4","Babar4",153));
        this.etudiants.add(new Etudiant("00000005","Babar5","Babar3",152));
        this.etudiants.add(new Etudiant("00000006","Babar6","Babar2",151));
        this.etudiants.add(new Etudiant("00000007","Babar7","Babar1",150));
    }

    public Collection<Etudiant> getEtudiants() {
        return etudiants;
    }



}
