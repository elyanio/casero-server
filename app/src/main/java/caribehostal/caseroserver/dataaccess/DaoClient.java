package caribehostal.caseroserver.dataaccess;

import caribehostal.caseroserver.datamodel.Client;
import io.requery.Persistable;
import io.requery.sql.EntityDataStore;

/**
 * Created by asio on 8/17/2017.
 */

public class DaoClient {
    private EntityDataStore<Persistable> dataStore;

    public DaoClient() {
        dataStore = DataStoreHolder.INSTANCE.getDataStore();
    }

    public void upsertClient(Client client) {
        dataStore.upsert(client);
    }
}
