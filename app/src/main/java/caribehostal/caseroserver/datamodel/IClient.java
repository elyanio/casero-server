package caribehostal.caseroserver.datamodel;

import io.requery.Column;
import io.requery.Entity;
import io.requery.Key;
import io.requery.Persistable;

import static io.requery.PropertyNameStyle.FLUENT_BEAN;

/**
 * @author Fernando
 */
@Entity(propertyNameStyle = FLUENT_BEAN)
public interface IClient extends Persistable {
    @Key
    @Column(nullable = false)
    String getPassport();
}
