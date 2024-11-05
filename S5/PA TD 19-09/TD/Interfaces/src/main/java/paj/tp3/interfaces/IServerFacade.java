package paj.tp3.interfaces;

import paj.tp3.abstracts.AbstractUser;
import paj.tp3.abstracts.Sondage;
import paj.tp3.abstracts.User;

import java.util.HashMap;
import java.util.List;

public interface IServerFacade {
    void createUser(User userToCreate);
    void createPoll(AbstractUser user, Sondage sondage);
    HashMap<String,Sondage> listPolls();
    Sondage getPoll(String uuid);
    List<Sondage> getPollCollaboratedIn(String uuid);
    void answerPoll(String userUuid, String pollUuid, int choice);
    String getProfileInfo(String uuid);
    String getInfoSondage(String uuid);
    void unsubscribe(String uuid);
}
