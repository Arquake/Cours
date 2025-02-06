import com.opensymphony.xwork2.ActionSupport;
import facade.FacadeParisStaticImpl;
import modele.Match;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Collection;
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
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setApplication(Map<String, Object> map) {
        FACADE = (FacadeParisStaticImpl) map.get("facade");
        if(Objects.isNull(FACADE)) {
            FACADE = new FacadeParisStaticImpl();
            map.put("facade", FACADE);
        }
    }
}
