package actions;

import com.opensymphony.xwork2.ActionProxy;
import modele.FacadeBonsPlans;
import modele.TokenExpireException;
import modele.exceptions.CandidatureDejaDeposeeException;
import modele.exceptions.UtilisateurInconnuException;
import org.apache.struts2.StrutsJUnit4TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class CandidaterTest extends StrutsJUnit4TestCase {

    Map<String, Object> application;
    Map<String, Object> session;
    FacadeBonsPlans facadeBonsPlans;

    @Before
    public void init() {
        session = new HashMap<>();
        application = new HashMap<>();

        facadeBonsPlans = mock(FacadeBonsPlans.class);

        session.put("token", "");
        application.put("facadeBonsPlans", facadeBonsPlans);
    }

    @Test
    public void toutBon() throws Exception {

        doNothing().when(facadeBonsPlans).candidaterModerateur("");

        ActionProxy actionProxy = getActionProxy("/candidater");
        actionProxy.getInvocation().getInvocationContext().setApplication(application);
        actionProxy.getInvocation().getInvocationContext().setSession(session);

        String res = actionProxy.execute();

        Assert.assertEquals("success", res);
    }

    @Test
    public void utilisateurInconnu() throws Exception {

        doThrow(UtilisateurInconnuException.class).when(facadeBonsPlans).candidaterModerateur("");

        ActionProxy actionProxy = getActionProxy("/candidater");
        actionProxy.getInvocation().getInvocationContext().setApplication(application);
        actionProxy.getInvocation().getInvocationContext().setSession(session);

        String res = actionProxy.execute();

        Assert.assertEquals("error", res);
    }

    @Test
    public void tokenExpire() throws Exception {

        doThrow(TokenExpireException.class).when(facadeBonsPlans).candidaterModerateur("");

        ActionProxy actionProxy = getActionProxy("/candidater");
        actionProxy.getInvocation().getInvocationContext().setApplication(application);
        actionProxy.getInvocation().getInvocationContext().setSession(session);

        String res = actionProxy.execute();

        Assert.assertEquals("error", res);
    }

    @Test
    public void alreadyDone() throws Exception {

        doThrow(CandidatureDejaDeposeeException.class).when(facadeBonsPlans).candidaterModerateur("");

        ActionProxy actionProxy = getActionProxy("/candidater");
        actionProxy.getInvocation().getInvocationContext().setApplication(application);
        actionProxy.getInvocation().getInvocationContext().setSession(session);

        String res = actionProxy.execute();

        Assert.assertEquals("input", res);
    }

}