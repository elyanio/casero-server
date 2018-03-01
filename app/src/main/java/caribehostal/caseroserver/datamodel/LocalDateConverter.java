package caribehostal.caseroserver.datamodel;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeParseException;

import io.requery.Converter;
import io.requery.Nullable;

/**
 * @author rainermf
 */
public class LocalDateConverter implements Converter<LocalDate, String> {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y-MM-dd");

    @Override
    public Class<LocalDate> getMappedType() {
        return LocalDate.class;
    }

    @Override
    public Class<String> getPersistedType() {
        return String.class;
    }

    @Nullable
    @Override
    public Integer getPersistedSize() {
        return null;
    }

    @Override
    public String convertToPersisted(LocalDate value) {
        return value.format(formatter);
    }

    @Override
    public LocalDate convertToMapped(Class<? extends LocalDate> type, String value) {
        try {
            return LocalDate.parse(value, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
