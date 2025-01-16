import beans.ConnexionErrorBean;
import facade.FacadeParisStaticImpl;
import facade.exceptions.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modele.Match;
import modele.Pari;
import modele.Utilisateur;

import java.io.IOException;

@WebServlet(name = "pel", urlPatterns = "/pel/*")
public class Controller extends HttpServlet {

    private static final String HOME = "home";
    private static final String ANNULER_PARI = "annulerpari";
    private static final String CONNEXION = "connexion";
    private static final String PARIS_OUVERTS = "parisouverts";
    private static final String MESPARIS = "mesparis";
    private static final String PARIER = "parier";
    private static final String DECONNEXION = "logout";

    private static FacadeParisStaticImpl FACADE;

    public Controller() {
        FACADE = new FacadeParisStaticImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();
        String[] parts = uri.split("/");
        String cleNavigation = parts[parts.length - 1];

        switch (cleNavigation) {
            case CONNEXION:
            case HOME:
                this.ConnexionGetHandler(req, resp);
                break;
            case ANNULER_PARI:
                this.annulerPari(req, resp);
                break;
            case PARIS_OUVERTS:
                this.parisOuverts(req, resp);
                break;
            case MESPARIS:
                this.mesParis(req, resp);
                break;
            case PARIER:
                this.parierHandler(req, resp);
                break;
            case DECONNEXION:
                this.deconnecter(req, resp);
                break;
            default:
                this.getServletContext().getRequestDispatcher("/pages/NotFound.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String[] parts = uri.split("/");
        String cleNavigation = parts[parts.length - 1];

        switch (cleNavigation) {
            case HOME:
            case CONNEXION:
                ConnexionPostHandler(req, resp);
                break;
            case ANNULER_PARI:
                this.annulerPari(req, resp);
                break;
            case PARIS_OUVERTS:
                this.parisOuverts(req,resp);
                break;
            case MESPARIS:
                this.mesParis(req,resp);
                break;
            case PARIER:
                this.parierHandler(req,resp);
                break;
            default:
                this.getServletContext().getRequestDispatcher("/pages/NotFound.jsp").forward(req, resp);
                break;
        };
    }

    private void ConnexionPostHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConnexionErrorBean connexionBean = new ConnexionErrorBean();

        if (isLoggedIn(req)) {
            this.getServletContext().getRequestDispatcher("/pages/Menu.jsp").forward(req, resp);
        }
        else {
            try {
                Utilisateur user = FACADE.connexion(req.getParameter("pseudo") ,req.getParameter("password"));
                (req.getSession()).setAttribute("user", user);
                this.getServletContext().getRequestDispatcher("/pages/Menu.jsp").forward(req, resp);
            } catch (UtilisateurDejaConnecteException e) {
                connexionBean.setAlreadyConnectedError(true);
                req.setAttribute("connexionBean", connexionBean);
                this.getServletContext().getRequestDispatcher("/pages/Connexion.jsp").forward(req, resp);
            } catch (InformationsSaisiesIncoherentesException e) {
                connexionBean.setIncoherentError(true);
                req.setAttribute("connexionBean", connexionBean);
                this.getServletContext().getRequestDispatcher("/pages/Connexion.jsp").forward(req, resp);
            }
        }
    }

    private void ConnexionGetHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConnexionErrorBean connexionBean = new ConnexionErrorBean();
        req.setAttribute("connexionBean", connexionBean);
        if (isLoggedIn(req)) {
            this.getServletContext().getRequestDispatcher("/pages/Menu.jsp").forward(req, resp);
        }
        else {
            this.getServletContext().getRequestDispatcher("/pages/Connexion.jsp").forward(req, resp);
        }
    }

    private void annulerPari(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!isLoggedIn(req)) {
            resp.sendRedirect("/pel/connexion");
            return;
        }

        try {
            long id = Long.parseLong(req.getParameter("id"));
            Pari pariAnnule = FACADE.getPari(id);
            FACADE.annulerPari(
                    ((Utilisateur) req.getSession().getAttribute("user")).getLogin(), id
            );
            req.setAttribute("pariAnnule", pariAnnule);
            this.getServletContext().getRequestDispatcher("/pages/AnnulerPari.jsp").forward(req, resp);
        }
        catch (Exception ignored) {
            try {
                req.setAttribute("paris", FACADE.getMesParis(
                        ((Utilisateur) req.getSession().getAttribute("user")).getLogin()
                ));
                req.setAttribute("cancelError", true);
                this.getServletContext().getRequestDispatcher("/pages/MesParis.jsp").forward(req, resp);
            }
            catch (Exception ignoredSecond) {
                this.getServletContext().getRequestDispatcher("/pages/NotFound.jsp").forward(req, resp);
            }
        }
    }

    private void parisOuverts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!isLoggedIn(req)) {
            resp.sendRedirect("/pel/connexion");
            return;
        }

        req.setAttribute("matchs", FACADE.getMatchsPasCommences());
        this.getServletContext().getRequestDispatcher("/pages/ParisOuverts.jsp").forward(req, resp);
    }

    private void parierHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!isLoggedIn(req)) {
            resp.sendRedirect("/pel/connexion");
            return;
        }

        try {
            if (req.getParameter("verdict") == null) {
                req.setAttribute("match", FACADE.getMatch(Long.parseLong(req.getParameter("matchId"))));
                req.setAttribute("verdict", "" );
                req.setAttribute("errorMise", false);
                this.getServletContext().getRequestDispatcher("/pages/Parier.jsp").forward(req, resp);
                return;
            }
            long pari = FACADE.parier(
                    ((Utilisateur) req.getSession().getAttribute("user")).getLogin(),
                    Long.parseLong(req.getParameter("matchId")),
                    req.getParameter("verdict"),
                    Long.parseLong(req.getParameter("mise"))
            );
            req.setAttribute("pari", FACADE.getPari(pari));
            this.getServletContext().getRequestDispatcher("/pages/PariValidation.jsp").forward(req, resp);

        } catch (MatchClosException e) {
            resp.sendRedirect("/pel/parisouverts");

        } catch (ResultatImpossibleException e) {
            req.setAttribute("match", FACADE.getMatch(Long.parseLong(req.getParameter("matchId"))));
            req.setAttribute("verdict", req.getParameter("verdict") != null?req.getParameter("verdict"):"" );
            req.setAttribute("errorMise", false);
            this.getServletContext().getRequestDispatcher("/pages/Parier.jsp").forward(req, resp);

        } catch (MontantNegatifOuNulException e) {
            req.setAttribute("match", FACADE.getMatch(Long.parseLong(req.getParameter("matchId"))));
            req.setAttribute("verdict", req.getParameter("verdict") != null?req.getParameter("verdict"):"" );
            req.setAttribute("errorMise", true);
            this.getServletContext().getRequestDispatcher("/pages/Parier.jsp").forward(req, resp);
        }
        catch (Exception e) {
            req.setAttribute("match", FACADE.getMatch(Long.parseLong(req.getParameter("matchId"))));
            req.setAttribute("verdict", req.getParameter("verdict") != null?req.getParameter("verdict"):"" );
            req.setAttribute("errorMise", true);
            this.getServletContext().getRequestDispatcher("/pages/Parier.jsp").forward(req, resp);
        }

    }

    private void mesParis(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!isLoggedIn(req)) {
            resp.sendRedirect("/pel/connexion");
            return;
        }

        try {
            req.setAttribute("paris", FACADE.getMesParis(
                    ((Utilisateur) req.getSession().getAttribute("user")).getLogin()
            ));
            req.setAttribute("cancelError", false);
            this.getServletContext().getRequestDispatcher("/pages/MesParis.jsp").forward(req, resp);
        }
        catch (Exception ignored) {
            this.getServletContext().getRequestDispatcher("/pages/NotFound.jsp").forward(req, resp);
        }
    }

    private void deconnecter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            FACADE.deconnexion(((Utilisateur) req.getSession().getAttribute("user")).getLogin());
            req.getSession().removeAttribute("user");
        }
        catch (Exception ignored) {}
        finally {
            resp.sendRedirect("/pel/connexion");
        }
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        try {
            String userLogin = ((Utilisateur) req.getSession().getAttribute("user")).getLogin();
            return userLogin != null;
        }
        catch (Exception ignored) {
            return false;
        }
    }
}
