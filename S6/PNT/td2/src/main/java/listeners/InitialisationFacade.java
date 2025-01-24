package listeners;

import facade.FacadeParisStaticImpl;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InitialisationFacade implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        FacadeParisStaticImpl facade = new FacadeParisStaticImpl();
        sce.getServletContext().setAttribute("facade", facade);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
