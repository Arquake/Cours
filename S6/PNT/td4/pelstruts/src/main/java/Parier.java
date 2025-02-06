import facade.FacadeParisStaticImpl;
import modele.Match;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import java.util.Map;
import java.util.Objects;

public class Parier extends ActionSupport implements ApplicationAware, SessionAware {

    private long idMatch;
    private Match match;
    private Map<String, Object> session;
    private static FacadeParisStaticImpl FACADE;

    public void setIdMatch(long idMatch) {
        this.idMatch = idMatch;
    }

    @Override
    public String execute() throws Exception {
        if (session.containsKey("user")) {
            match = FACADE.getMatch(idMatch);
            return SUCCESS;
        }
        return ERROR;
    }

    public Match getMatch() {
        return match;
    }

    @Override
    public void withApplication(Map<String, Object> map) {
        FACADE = (FacadeParisStaticImpl) map.get("facade");
        if(Objects.isNull(FACADE)) {
            FACADE = new FacadeParisStaticImpl();
            map.put("facade", FACADE);
        }
    }

    @Override
    public void withSession(Map<String, Object> session) {
        this.session = session;
    }
}
