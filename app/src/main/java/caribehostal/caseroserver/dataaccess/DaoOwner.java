package caribehostal.caseroserver.dataaccess;


import caribehostal.caseroserver.datamodel.Owner;
import io.requery.Persistable;
import io.requery.sql.EntityDataStore;

/**
 * Created by asio on 8/17/2017.
 */

public class DaoOwner {

    public void insertOwner(Owner owner) {
        EntityDataStore<Persistable> dataStore = DataStoreHolder.INSTANCE.getDataStore();
        dataStore.insert(owner);
    }
}
