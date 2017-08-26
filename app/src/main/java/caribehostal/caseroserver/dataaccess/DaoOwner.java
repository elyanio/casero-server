package caribehostal.caseroserver.dataaccess;


import java.util.ArrayList;

import caribehostal.caseroserver.datamodel.Owner;
import io.requery.Persistable;
import io.requery.query.Result;
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

    public Result<Owner> getAllOwner() {
        ArrayList<Owner> owners = new ArrayList<>();
        return dataStore.select(Owner.class).orderBy(Owner.FULL_NAME).get();

    }

    public Owner getOwner(String cell) {
        return dataStore.select(Owner.class).where(Owner.CELL.eq(cell)).get().firstOrNull();
    }

    public void remove(Owner owner) {
        dataStore.delete(owner);
    }
}
