package modele;

import java.util.List;

public interface FacadeModele {

    List<Cheval> getAll();

    Cheval getChevalById(long id) throws ChevalNotFound;

    void ajouterCheval(String nom, int age) throws ChevalDejaExistant;

}
