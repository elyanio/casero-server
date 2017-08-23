package caribehostal.caseroserver.dataaccess;


import java.util.List;

import caribehostal.caseroserver.datamodel.Owner;
import io.requery.Persistable;
import io.requery.sql.EntityDataStore;

/**
 * Created by asio on 8/17/2017.
 */

public class DaoOwner {

    private EntityDataStore<Persistable> dataStore;

    public DaoOwner() {
        dataStore = DataStoreHolder.INSTANCE.getDataStore();
    }

    public void upsertOwner(Owner owner) {
        dataStore.upsert(owner);
    }

    public List<Owner> getAllOwner() {
        return dataStore.select(Owner.class).orderBy(Owner.FULL_NAME).get().toList();
    }
}
