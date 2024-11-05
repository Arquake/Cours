package paj.tp3.server.Interfaces;

import paj.tp3.server.AbstractUser;
import paj.tp3.server.Sondage;
import paj.tp3.server.User;

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
