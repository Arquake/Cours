package actions;

import com.opensymphony.xwork2.ActionProxy;
import modele.BonPlan;
import modele.FabriqueBonPlan;
import modele.FacadeBonsPlans;
import modele.exceptions.BonPlanInconnuException;
import org.apache.struts2.StrutsJUnit4TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import outils.DateBuilderFromString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class ModifierBonPlanTest extends StrutsJUnit4TestCase {

    Map<String, Object> application;
    Map<String, Object> session;
    FacadeBonsPlans facadeBonsPlans;
    FabriqueBonPlan fabriqueBonPlan;

    String debut;
    String fin;
    String id;
    String description;
    String theme;
    String lien;
    String prix;
    BonPlan bp;

    @Before
    public void init() {
        session = new HashMap<>();
        application = new HashMap<>();

        facadeBonsPlans = mock(FacadeBonsPlans.class);
        fabriqueBonPlan = mock(FabriqueBonPlan.class);

        session.put("token", "");
        application.put("facadeBonsPlans", facadeBonsPlans);
        application.put("fabriqueBonPlan", fabriqueBonPlan);

        debut = "10-12-2025 10:10";
        fin = "11-12-2025 10:10";
        id = "1";
        description = "test";
        theme = "test";
        lien = "test";
        prix = "23.5";

        bp = mock(BonPlan.class);
    }

    @Test
    public void ok() throws Exception {
        doReturn(bp).when(fabriqueBonPlan).creer(Integer.parseInt(id), description, theme, lien, Double.parseDouble(prix), getDate(debut), getDate(fin));
        doNothing().when(facadeBonsPlans).modifierBonPlan("", bp);

        verifyFor("success");
    }

    @Test(expected = BonPlanInconnuException.class)
    public void bonPlanInconnuException() throws Exception {
        verifyFor("unchecked");
    }

    @Test
    public void dateTimeParseException() throws Exception {
        verifyFor("input");
    }

    @Test
    public void tokenExpireException() throws Exception {
        verifyFor("error");
    }

    @Test
    public void utilisateurInconnuException() throws Exception {
        verifyFor("error");
    }

    @Test(expected = RuntimeException.class)
    public void droitsInsuffisantsException() throws Exception {

        verifyFor("unchecked");
    }

    private void verifyFor(String expectedRed) throws Exception {
        request.addParameter("dateDebut", debut);
        request.addParameter("dateFin", fin);
        request.addParameter("id", id);
        request.addParameter("description", description);
        request.addParameter("theme", theme);
        request.addParameter("lien", lien);
        request.addParameter("prix", prix);

        ActionProxy actionProxy = getActionProxy("/modifierbonplan");
        actionProxy.getInvocation().getInvocationContext().setApplication(application);
        actionProxy.getInvocation().getInvocationContext().setSession(session);
        String res = actionProxy.execute();

        Assert.assertEquals(expectedRed, res);
    }

    private LocalDateTime getDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm"));
    }
}