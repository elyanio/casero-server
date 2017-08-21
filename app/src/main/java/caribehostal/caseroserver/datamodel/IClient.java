package caribehostal.caseroserver.datamodel;

import io.requery.Column;
import io.requery.Entity;
import io.requery.Key;

/**
 * Created by Fernando on 16/08/2017.
 */
@Entity
public interface IClient {
    @Key
    @Column(nullable = false)
    String getPassport();
}
