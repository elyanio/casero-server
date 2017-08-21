package caribehostal.caseroserver.dataaccess;

import caribehostal.caseroserver.datamodel.Action;
import io.requery.Persistable;
import io.requery.sql.EntityDataStore;

/**
 * Created by asio on 8/17/2017.
 */

public class DaoAction {

    public void insertAction(Action action) {
        EntityDataStore<Persistable> dataStore = DataStoreHolder.INSTANCE.getDataStore();
        dataStore.insert(action);
    }
}
