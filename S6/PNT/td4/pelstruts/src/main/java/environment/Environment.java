package environment;

import facade.FacadeParisStaticImpl;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.SessionAware;

import java.util.Map;
import java.util.Objects;

public class Environment extends ActionSupport implements SessionAware, ApplicationAware {

    protected Map<String, Object> session;
    protected static FacadeParisStaticImpl FACADE;

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
