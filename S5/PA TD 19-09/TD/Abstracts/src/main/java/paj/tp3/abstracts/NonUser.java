package paj.tp3.abstracts;

import paj.tp3.rmi.RMI;
import paj.tp3.rmi.exceptions.Exceptions.InvalidDataUserCreationException;
import paj.tp3.rmi.exceptions.Exceptions.InvalidInputException;

import java.rmi.RemoteException;


public class NonUser extends AbstractUser {

    public NonUser() {
        super(false);
    }

    /**
     * Crée le nouvel utilisateur
     * @return L'utilisateur créé
     * @throws InvalidDataUserCreationException si l'utilisateur à rentré des paramètres éronné
     */
    public AbstractUser subscribe(RMI rmi) throws InvalidDataUserCreationException {
        String nom = getUserInput("nom : ");
        String prenom = getUserInput("prenom : ");
        String email = getUserInput("email : ");
        String password = getUserInput("mot de passe : ");

        try {
            return rmi.createUser(nom, prenom, email, password);
        } catch (InvalidInputException e) {
            System.err.println(e.getMessage());
            throw new InvalidDataUserCreationException();
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
            throw new InvalidDataUserCreationException();
        }

    }
}
