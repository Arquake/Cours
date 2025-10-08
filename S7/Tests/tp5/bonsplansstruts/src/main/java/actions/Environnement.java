package actions;

import com.opensymphony.xwork2.ActionSupport;
import modele.*;
import modele.exceptions.UtilisateurInconnuException;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.time.LocalDateTime;
import java.util.Map;

public abstract class Environnement extends ActionSupport implements ApplicationAware, SessionAware {
    private FacadeBonsPlans facadeBonsPlans;
    private Map<String, Object> session;

    private FabriqueBonPlan fabriqueBonPlan;


    public FabriqueBonPlan getFabriqueBonPlan() {
        return fabriqueBonPlan;
    }


    @Override
    public void setApplication(Map<String, Object> map) {
        facadeBonsPlans = (FacadeBonsPlans) map.get("facadeBonsPlans");
        if (facadeBonsPlans == null) {
            facadeBonsPlans = null;
            map.put("facadeBonsPlans", facadeBonsPlans);
        }


        fabriqueBonPlan = (FabriqueBonPlan) map.get("fabriqueBonPlan");
        if (fabriqueBonPlan == null) {
            fabriqueBonPlan =  null;
            map.put("fabriqueBonPlan", fabriqueBonPlan);
        }

    }


    public FacadeBonsPlans getFacadeBonsPlans() {
        return facadeBonsPlans;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }


    public String getToken() {
        return (String) session.get("token");
    }

    public Utilisateur getUtilisateur() throws TokenExpireException, UtilisateurInconnuException {
        return facadeBonsPlans.getUtilisateur(getToken());
    }



}
