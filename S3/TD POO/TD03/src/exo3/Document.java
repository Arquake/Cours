package exo3;

abstract public class Document {
    int numDoc;
    String titre;

    public Document(int numDoc, String titre) {
        this.numDoc = numDoc;
        this.titre = titre;
    }

    @Override
    public String toString(){
        return "titre : "+titre+" numéro document : "+numDoc;
    }

    public abstract int getNbJoursAutorises();
}
