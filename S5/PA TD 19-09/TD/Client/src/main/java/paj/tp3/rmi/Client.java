package paj.tp3.rmi;

import paj.tp3.abstracts.AbstractUser;
import paj.tp3.abstracts.NonUser;

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
