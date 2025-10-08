package outils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class AbstractDateBuilder implements DateBuilderFromString{

    private AbstractDateBuilder next;
    private String schema;

    public AbstractDateBuilder(AbstractDateBuilder next, String schema) {
        this.next = next;
        this.schema = schema;
    }

    @Override
    public LocalDateTime stringToLocalDateTime(String dateStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(schema);
        try {
            LocalDate dateTime = LocalDate.parse(dateStr, formatter);
            return dateTime.atStartOfDay();
        }
        catch (DateTimeParseException e) {
            if (next != null) {
                return next.stringToLocalDateTime(dateStr);
            }
            else {
                throw e;
            }
        }
    }

}
