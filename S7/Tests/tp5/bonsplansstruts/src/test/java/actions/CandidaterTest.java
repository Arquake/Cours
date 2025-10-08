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

    @Before
    public void init() {
        session = new HashMap<>();
        application = new HashMap<>();
    }

    @Test
    public void toutBon() throws Exception {

        FacadeBonsPlans facadeBonsPlans = mock(FacadeBonsPlans.class);

        doNothing().when(facadeBonsPlans).candidaterModerateur("");

        application.put("facadeBonsPlans", facadeBonsPlans);

        ActionProxy actionProxy = getActionProxy("/candidater");
        actionProxy.getInvocation().getInvocationContext().setApplication(application);
        actionProxy.getInvocation().getInvocationContext().setSession(session);

        String res = actionProxy.execute();

        Assert.assertEquals("success", res);
    }

    @Test
    public void utilisateurInconnu() throws Exception {

        FacadeBonsPlans facadeBonsPlans = mock(FacadeBonsPlans.class);

        doThrow(UtilisateurInconnuException.class).when(facadeBonsPlans).candidaterModerateur("");

        application.put("facadeBonsPlans", facadeBonsPlans);

        ActionProxy actionProxy = getActionProxy("/candidater");
        actionProxy.getInvocation().getInvocationContext().setApplication(application);
        actionProxy.getInvocation().getInvocationContext().setSession(session);

        String res = actionProxy.execute();

        Assert.assertEquals("error", res);
    }

    @Test
    public void tokenExpire() throws Exception {

        FacadeBonsPlans facadeBonsPlans = mock(FacadeBonsPlans.class);

        doThrow(TokenExpireException.class).when(facadeBonsPlans).candidaterModerateur("");

        application.put("facadeBonsPlans", facadeBonsPlans);

        ActionProxy actionProxy = getActionProxy("/candidater");
        actionProxy.getInvocation().getInvocationContext().setApplication(application);
        actionProxy.getInvocation().getInvocationContext().setSession(session);

        String res = actionProxy.execute();

        Assert.assertEquals("error", res);
    }

    @Test
    public void alreadyDone() throws Exception {

        FacadeBonsPlans facadeBonsPlans = mock(FacadeBonsPlans.class);

        doThrow(CandidatureDejaDeposeeException.class).when(facadeBonsPlans).candidaterModerateur("");

        application.put("facadeBonsPlans", facadeBonsPlans);

        ActionProxy actionProxy = getActionProxy("/candidater");
        actionProxy.getInvocation().getInvocationContext().setApplication(application);
        actionProxy.getInvocation().getInvocationContext().setSession(session);

        String res = actionProxy.execute();

        Assert.assertEquals("input", res);
    }

}