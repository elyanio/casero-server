package caribehostal.caseroserver.dataaccess;

import java.util.List;

import caribehostal.caseroserver.datamodel.Action;
import caribehostal.caseroserver.datamodel.ActionClient;
import caribehostal.caseroserver.datamodel.Client;
import io.requery.Persistable;
import io.requery.sql.EntityDataStore;

/**
 * @author asio
 */
public class DaoClient {

    private EntityDataStore<Persistable> dataStore;

    public DaoClient() {
        dataStore = DataStoreHolder.INSTANCE.getDataStore();
    }

    public void upsertClient(Client client) {
        dataStore.upsert(client);
    }

    public List<Client> findClientsInAction(Action action) {
        return dataStore.select(Client.class)
                .join(ActionClient.class)
                .on(Client.PASSPORT.eq(ActionClient.CLIENT_ID))
                .where(ActionClient.ACTION.eq(action))
                .get()
                .toList();
    }

}
