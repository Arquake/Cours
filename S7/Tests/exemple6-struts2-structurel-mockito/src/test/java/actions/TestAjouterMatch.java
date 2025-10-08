package actions;

import com.opensymphony.xwork2.ActionProxy;
import facade.FacadeParis;
import modele.Match;
import modele.Utilisateur;
import org.apache.struts2.StrutsJUnit4TestCase;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


public class TestAjouterMatch extends StrutsJUnit4TestCase {


    private static String etendreSiNecessaire(int x) {
        if (x <10) {
            return "0"+x;
        }
        else
            return Integer.toString(x);
    }



    @Test
    public void testOk() throws Exception {


        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        request.addParameter("equipe1","PSG");
        request.addParameter("equipe2","OM");
        request.addParameter("sport","football");
        request.addParameter("quand","2024-10-27 21:00");

        Utilisateur utilisateur = Mockito.mock(Utilisateur.class);
        FacadeParis facadeParis = Mockito.mock(FacadeParis.class);
        Match matchCree = Mockito.mock(Match.class);


        Map<String,Object> session = new HashMap<>();
        Map<String,Object> application = new HashMap<>();

        application.put("facade",facadeParis);
        session.put("user",utilisateur);

        ActionProxy actionProxy = getActionProxy("/ajouterMatch");
        actionProxy.getInvocation()
                .getInvocationContext()
                .setApplication(application);
        actionProxy.getInvocation().getInvocationContext()
                .setSession(session);


        doReturn("Yoh").when(utilisateur)
                .getLogin();

        doReturn(102l).when(facadeParis)
                .ajouterMatch(eq("Yoh"),eq("football"),
                        eq("PSG"),
                        eq("OM"),
                        ArgumentMatchers.isA(LocalDateTime.class));

        doReturn(matchCree).when(facadeParis)
                .getMatch(102l);



        String resultat = actionProxy.execute();



        assertEquals("success",resultat);
        Mockito.verify(facadeParis,times(1)).ajouterMatch(eq("Yoh"),any(),any(),any(),any());
        Mockito.verify(facadeParis,times(1)).getMatch(eq(102l));
        Mockito.verify(utilisateur,times(1)).getLogin();


    }



    @Test
    public void testKODateAnterieure() throws Exception {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        request.addParameter("equipe1","PSG");
        request.addParameter("equipe2","OM");
        request.addParameter("sport","football");
        request.addParameter("quand","2023-10-27 21:00");

        FacadeParis facadeParis = Mockito.mock(FacadeParis.class);

        Map<String,Object> application = new HashMap<>();

        application.put("facade",facadeParis);

        ActionProxy actionProxy = getActionProxy("/ajouterMatch");
        actionProxy.getInvocation()
                .getInvocationContext()
                .setApplication(application);

        AjouterMatch ajouterMatch = (AjouterMatch) actionProxy.getAction();
        String resultat = actionProxy.execute();

        assertEquals("input",resultat);
        assertEquals(1,ajouterMatch.getFieldErrors().get("quand").size());

    }

}
