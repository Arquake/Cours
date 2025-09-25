package modele.constructionOperande;

public interface EtatConstructionOperande {

    EtatConstructionOperande inputDigit(int x);

    EtatConstructionOperande inputDot();
    double getOperandeCourante();

    String getOperandeCouranteString();

    EtatConstructionOperande reset();
}
