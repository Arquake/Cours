package outils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DateBuilderForJJMMAAAA extends AbstractDateBuilder {


    public DateBuilderForJJMMAAAA(AbstractDateBuilder next) {
        super(next, "dd/MM/yyyy");
    }




}
