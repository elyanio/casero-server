package caribehostal.caseroserver.datamodel;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import io.requery.Converter;

/**
 * Created by asio on 9/1/2017.
 */

public class LocalDateTimeConverter implements Converter<LocalDateTime, String> {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y-MM-dd HH:mm:ss");

    @Override
    public Class<LocalDateTime> getMappedType() {
        return LocalDateTime.class;
    }

    @Override
    public Class<String> getPersistedType() {
        return String.class;
    }

    @Override
    public Integer getPersistedSize() {
        return null;
    }

    @Override
    public String convertToPersisted(LocalDateTime value) {
        if (value != null) {
            return value.format(formatter);
        } else {
            return "null";
        }
    }

    @Override
    public LocalDateTime convertToMapped(Class<? extends LocalDateTime> type, String value) {
        if (!value.equals("null")) {
            return LocalDateTime.parse(value, formatter);
        } else {
            return null;
        }
    }
}
