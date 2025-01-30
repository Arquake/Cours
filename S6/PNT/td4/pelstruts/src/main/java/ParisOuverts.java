import com.opensymphony.xwork2.ActionSupport;
import facade.FacadeParisStaticImpl;
import modele.Match;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class ParisOuverts extends ActionSupport implements SessionAware, ApplicationAware {

    private Map<String, Object> session;

    private static FacadeParisStaticImpl FACADE;

    private Collection<Match> matchs;

    @Override
    public String execute() throws Exception {
        if (session.containsKey("user")) {
            matchs = FACADE.getMatchsPasCommences();
            return SUCCESS;
        }
        return ERROR;
    }

    public Collection<Match> getMatchs() {
        return matchs;
    }

    public Map<String, Object> getSession() {
        return session;
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
