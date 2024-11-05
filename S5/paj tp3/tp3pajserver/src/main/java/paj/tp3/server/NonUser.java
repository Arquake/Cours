package paj.tp3.server;

import Exceptions.InvalidInputException;
import RMI.API;
import Server.Exceptions.InvalidDataUserCreationException;

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
    public AbstractUser subscribe() throws InvalidDataUserCreationException {
        String nom = getUserInput("nom : ");
        String prenom = getUserInput("prenom : ");
        String email = getUserInput("email : ");
        String password = getUserInput("mot de passe : ");

        try {
            return API.createUser(nom, prenom, email, password);
        } catch (InvalidInputException e) {
            System.err.println(e.getMessage());
            throw new InvalidDataUserCreationException();
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
            throw new InvalidDataUserCreationException();
        }

    }
}
