package facadeLudotheque;

import modeleLudotheque.CategorieJeu;
import modeleLudotheque.Jeu;

import java.util.*;
import java.util.stream.Collectors;

public class FacadeLudothequeImpl implements FacadeLudotheque {

    private Map<CategorieJeu, Collection<Jeu>> lesJeuxParCategorie;


    public FacadeLudothequeImpl() {
        this.lesJeuxParCategorie = new HashMap<>();
        for (CategorieJeu c : CategorieJeu.values()) {
            this.lesJeuxParCategorie.put(c,new ArrayList<>());
        }
        this.initialisation();
    }


    private void initialisation() {

        Collection<Jeu> tous = Arrays.asList(
                new Jeu("Citadelle", CategorieJeu.STRATEGIE, "Faites preuve de stratégie dans le choix des personnages pour construire la plus belle citée"),
                new Jeu("Stratego",CategorieJeu.STRATEGIE,"Protégez votre drapeau et traquez celui de l'adversaire"),
                new Jeu("Bonne paie", CategorieJeu.FAMILIAL, "Payez vos factures, investissez, devenez le plus riche"),
                new Jeu("Monopoly",CategorieJeu.FAMILIAL,"Achetez des propriétés, construisez des immeubles et ruinez vos adversaires !"),
                new Jeu("Tarot",CategorieJeu.CARTES,"L'un des jeux les plus populaires. Parvenez à remplir vos contrats et empêchez vos adversaires de le faire."),
                new Jeu("Belote",CategorieJeu.CARTES,"En équipe, atteignez les 82 points si vous prenez et empêchez vos adversaires de le faire quand ils prennent.")
        );

        for (Jeu p : tous) {
            Collection<Jeu> jeux = this.lesJeuxParCategorie.get(p.getCategorieJeu());
            jeux.add(p);
        }
    }

    @Override
    public Collection<String> getAlls() {

        return this.lesJeuxParCategorie.keySet().stream().map(x -> x.toString())
                .collect(Collectors.toList());
    }



    @Override
    public Collection<Jeu> getJeuxDuneCategorie(String categorie) throws CategorieNotFoundException {
        try {
            CategorieJeu categorieJeu = CategorieJeu.valueOf(categorie);
            if (!Objects.isNull(categorieJeu) && this.lesJeuxParCategorie.containsKey(categorieJeu)){
                return this.lesJeuxParCategorie.get(categorieJeu);
            }
            throw new CategorieNotFoundException();
        }
        catch (IllegalArgumentException e) {
            throw new CategorieNotFoundException();
        }

    }

    private boolean testAll(String ... s){
        for(String x : s ){
            if (Objects.isNull(x) || x.isEmpty())
                return true;
        }
        return false;
    }

    @Override
    public Integer ajoutJeu(String nomJeu, String categorie, String presentation) throws JeuDejaExistant, CategorieNotFoundException, InformationManquanteException {

        if (testAll(nomJeu,categorie,presentation)) {
            throw new InformationManquanteException();
        }


        for (CategorieJeu categorieJeu : this.lesJeuxParCategorie.keySet()) {
            for (Jeu jeu : this.lesJeuxParCategorie.get(categorieJeu)) {
                if (jeu.getNomJeu().equals(nomJeu)) {
                    throw new JeuDejaExistant();
                }
            }
        }

        try {
            Jeu jeu = new Jeu(nomJeu,CategorieJeu.valueOf(categorie),presentation);
            this.lesJeuxParCategorie.get(jeu.getCategorieJeu()).add(jeu);
            return jeu.getId();
        }
        catch (IllegalArgumentException e) {
            throw new CategorieNotFoundException();
        }
    }

    @Override
    public Jeu getJeuById(Integer id) throws JeuNotFoundException {
        for (CategorieJeu categorieJeu : this.lesJeuxParCategorie.keySet()) {
            for (Jeu jeu : this.lesJeuxParCategorie.get(categorieJeu)) {
                if (jeu.getId() == id) {
                    return jeu;
                }
            }
        }
        throw new JeuNotFoundException();
    }
}
