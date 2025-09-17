package fr.orleans.m1miage.wsi.ex2.exceptions;

public class ExceptionIncoherentUserInformations extends Exception {
    public ExceptionIncoherentUserInformations() {
        super("Les informations données ne sont pas cohérentes avec les données connus");
    }
}
