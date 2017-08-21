package caribehostal.caseroserver.dataaccess;

import caribehostal.caseroserver.datamodel.Client;
import io.requery.Persistable;
import io.requery.sql.EntityDataStore;

/**
 * Created by asio on 8/17/2017.
 */

public class DaoClient {

    public void insertClient(Client client) {
        EntityDataStore<Persistable> dataStore = DataStoreHolder.INSTANCE.getDataStore();
        dataStore.insert(client);
    }
}
