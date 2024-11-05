package paj.tp3.abstracts;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Sondage {
    private final String uuid;
    private final String libelle;
    private final HashMap<String, Integer> results;

    private final HashMap<Integer, String> resultsIntValues;
    private final HashMap<String, Integer> resultsValuesInt;

    /**
     * Donné un libellé et les choix crée un sondage
     * @param choices liste de choix possibles
     * @param libelle le titre du sondage
     */
    public Sondage(String libelle, List<String> choices) {

        this.results = new HashMap<>();
        this.resultsIntValues = new HashMap<>();
        this.resultsValuesInt = new HashMap<>();

        this.uuid = String.valueOf(UUID.randomUUID());
        this.libelle = libelle;
        int i = 0;
        for (String item : choices) {
            i++;
            resultsIntValues.put(i, item);
            resultsValuesInt.put(item, i);
            results.put(item, 0);
        }
    }

    public HashMap<String, Integer> getResults() {
        return (HashMap<String, Integer>) this.results.clone();
    }

    public String getLibelle() {
        return this.libelle;
    }

    public String getUuid() {
        return this.uuid;
    }

    @Override
    public String toString() {
        StringBuilder pollInfo = new StringBuilder(STR."id : \{uuid} | libellé : \{libelle}\n choix : ");
        for (String answer : results.keySet()) {
            pollInfo.append(resultsValuesInt.get(answer)).append(" :").append(answer).append(" | ");
        }
        return pollInfo.toString();
    }

    public void addOneTo(int choice) throws ChoiceDontExistException {
        String valueToIncrement = resultsIntValues.get(choice);
        if(valueToIncrement == null) {
            throw new ChoiceDontExistException();
        }

        results.put(valueToIncrement, results.get(valueToIncrement) + 1) ;
    }


}
