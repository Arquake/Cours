import facade.FacadeParisStaticImpl;
import facade.exceptions.InformationsSaisiesIncoherentesException;
import facade.exceptions.UtilisateurDejaConnecteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "pel", urlPatterns = "/pel/*")
public class Controller extends HttpServlet {

    private static final String HOME = "home";
    private static final String ANNULER_PARI = "annulerpari";
    private static final String CONNEXION = "connexion";
    private static final String PARIS_OUVERTS = "parisouverts";
    private static final String MESPARIS = "mesparis";
    private static final String PARIER = "parier";

    private static FacadeParisStaticImpl FACADE;

    public Controller() {
        FACADE = new FacadeParisStaticImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();
        String[] parts = uri.split("/");
        String cleNavigation = parts[parts.length - 1];
        String destination = "/pages/";

        destination += switch (cleNavigation) {
            case HOME -> "Home";
            case ANNULER_PARI -> "AnnulerPari";
            case CONNEXION -> "Connexion";
            case PARIS_OUVERTS -> "ParisOuverts";
            case MESPARIS -> "MesParis";
            case PARIER -> "Parier";
            default -> "NotFound";
        } + ".jsp";

        this.getServletContext().getRequestDispatcher(destination).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String[] parts = uri.split("/");
        String cleNavigation = parts[parts.length - 1];

        switch (cleNavigation) {
            case HOME:
                tryConnectionHome(req, resp);
                break;
            case CONNEXION:
                tryConnectionConnexion(req, resp);
                break;
            case ANNULER_PARI:
                this.getServletContext().getRequestDispatcher("/pages/NotFound.jsp").forward(req, resp);
                break;
            case PARIS_OUVERTS:
                this.getServletContext().getRequestDispatcher("/pages/NotFound.jsp").forward(req, resp);
                break;
            case MESPARIS:
                this.getServletContext().getRequestDispatcher("/pages/NotFound.jsp").forward(req, resp);
                break;
            case PARIER:
                this.getServletContext().getRequestDispatcher("/pages/NotFound.jsp").forward(req, resp);
                break;
            default:
                this.getServletContext().getRequestDispatcher("/pages/NotFound.jsp").forward(req, resp);
                break;
        };
    }


    private void tryConnectionHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            FACADE.connexion(req.getParameter("pseudo") ,req.getParameter("password"));
            this.getServletContext().getRequestDispatcher("/pages/Home.jsp").forward(req, resp);
        } catch (UtilisateurDejaConnecteException e) {
            resp.sendRedirect("connexion");
        } catch (InformationsSaisiesIncoherentesException e) {
            resp.sendRedirect("connexion");
        }
    }

    private void tryConnectionConnexion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            FACADE.connexion(req.getParameter("pseudo") ,req.getParameter("password"));
            this.getServletContext().getRequestDispatcher("/pages/Connexion.jsp").forward(req, resp);
        } catch (UtilisateurDejaConnecteException e) {
            req.setAttribute("connected", true);
            this.getServletContext().getRequestDispatcher("/pages/Connexion.jsp").forward(req, resp);
        } catch (InformationsSaisiesIncoherentesException e) {
            req.setAttribute("incoherent", true);
            this.getServletContext().getRequestDispatcher("/pages/Connexion.jsp").forward(req, resp);
        }

    }
}
