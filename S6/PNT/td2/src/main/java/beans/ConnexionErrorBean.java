package beans;

public class ConnexionErrorBean {

    private boolean alreadyConnectedError = false;

    private boolean incoherentError = false;

    public ConnexionErrorBean() {}

    public boolean isAlreadyConnectedError() {
        return alreadyConnectedError;
    }

    public boolean isIncoherentError() {
        return incoherentError;
    }

    public void setAlreadyConnectedError(boolean alreadyConnectedError) {
        this.alreadyConnectedError = alreadyConnectedError;
    }

    public void setIncoherentError(boolean incoherentError) {
        this.incoherentError = incoherentError;
    }
}
