package outils;

public class DateBuilderForInternationalFormat extends AbstractDateBuilder {


    public DateBuilderForInternationalFormat(AbstractDateBuilder next) {

        super(next, "dd-MM-yyyy HH:mm:ss");
    }

}
