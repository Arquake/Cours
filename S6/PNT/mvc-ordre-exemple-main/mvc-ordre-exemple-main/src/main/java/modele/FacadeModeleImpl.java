package modele;


import java.util.ArrayList;
import java.util.List;

public class FacadeModeleImpl implements FacadeModele{

    private List<Cheval> chevaux;

    public FacadeModeleImpl() {
        this.chevaux = new ArrayList<>();
        this.chevaux.add(new Cheval("Jolly Jumper",41));
    }

    @Override
    public List<Cheval> getAll() {
        return chevaux;
    }

    @Override
    public Cheval getChevalById(long id) throws ChevalNotFound {
        for (Cheval cheval : chevaux) {
            if (cheval.getId() == id)
                return cheval;
        }

        throw new ChevalNotFound();
    }

    @Override
    public void ajouterCheval(String nom, int age) throws ChevalDejaExistant {
        for (Cheval cheval : this.chevaux) {
            if (cheval.getNomCheval().equals(nom))
                throw new ChevalDejaExistant();
        }
        Cheval cheval = new Cheval(nom,age);
        this.chevaux.add(cheval);
    }
}
