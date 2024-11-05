package paj.tp3.client;

import Server.Classes.AbstractUser;
import Server.Classes.NonUser;

public class Client {

    public Client() {
        AbstractUser currenUser = new NonUser();

        if(currenUser.isAuthenticated()) {
            System.out.println("Auth !");
        }
        else {
            NonUser newUser = (NonUser) currenUser;
            try {
                currenUser = newUser.subscribe();
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
