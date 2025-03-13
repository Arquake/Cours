package controleur.abstractControleur;
import controleur.Controleur;
import controleur.ordres.EcouteurOrdre;
import controleur.ordres.TypeOrdre;
import vues.GestionnaireVue;

import java.util.*;

public abstract class AbstractControleur implements Controleur {
    private GestionnaireVue abstractGestionnaireVue;
    private Map<TypeOrdre, Collection<EcouteurOrdre>> abonnes;
    public GestionnaireVue getGestionnaireVue() {
        return abstractGestionnaireVue;
    }

    public AbstractControleur(GestionnaireVue gestionnaireVue) {
        this.abstractGestionnaireVue = gestionnaireVue;
        this.abonnes = new HashMap<>();
        Arrays.stream(TypeOrdre.values()).forEach(t ->
        {this.abonnes.put(t,new ArrayList<>());});

    }
    @Override
    public void abonnement(EcouteurOrdre ecouteurOrdre, TypeOrdre... types) {
        Arrays.stream(types).forEach(t ->
                this.abonnes.get(t).add(ecouteurOrdre));
    }
    @Override
    public void fireOrdre(TypeOrdre e) {
        this.abonnes.get(e).stream().forEach(ec ->
                ec.traiter(e));
    }
    public abstract void run();
}
