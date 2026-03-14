package main.model;

public class ClavierAdaptateur implements Manette {

    private Clavier clavier;

    public ClavierAdaptateur(Clavier clavier) {
        this.clavier = clavier;
    }

    @Override
    public boolean isLeft() {
        return clavier.keyPressed().equals(Clavier.Key.ARROW_LEFT);
    }

    @Override
    public boolean isRight() {
        return clavier.keyPressed().equals(Clavier.Key.ARROW_RIGHT);
    }

    @Override
    public boolean isPush() {
        return clavier.keyPressed().equals(Clavier.Key.SPACEBAR);
    }
}
