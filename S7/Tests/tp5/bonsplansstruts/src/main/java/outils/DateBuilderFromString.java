package outils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public interface DateBuilderFromString {

    LocalDateTime stringToLocalDateTime(String dateStr) throws DateTimeParseException;

     DateBuilderFromString builder= new DateBuilderForJJMMAAAA(new DateBuilderForInternationalFormat(null));


}
