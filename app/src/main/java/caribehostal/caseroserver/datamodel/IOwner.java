package caribehostal.caseroserver.datamodel;

import io.requery.Column;
import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.Persistable;

import static io.requery.PropertyNameStyle.FLUENT_BEAN;

/**
 * Created by Fernando on 16/08/2017.
 */
@Entity(propertyNameStyle = FLUENT_BEAN)
public interface IOwner extends Persistable {
    @Key
    @Column(nullable = false)
    String getCarnetId();

    @Column(nullable = false)
    String getFullName();

    @Column(nullable = false)
    String getUser();

    @Column(nullable = false)
    String getPassword();

    @Column(nullable = false)
    String getCell();

    @Column(nullable = false)
    String getAddress();

    @Column(nullable = true)
    String getAddressDescription();
}
