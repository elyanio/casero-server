package caribehostal.caseroserver.dataaccess;

import caribehostal.caseroserver.datamodel.Action;
import caribehostal.caseroserver.datamodel.ActionClient;
import caribehostal.caseroserver.datamodel.Client;
import io.requery.Persistable;
import io.requery.query.Result;
import io.requery.sql.EntityDataStore;

/**
 * Created by Fernando on 22/08/2017.
 */

public class DaoActionClient {

    private EntityDataStore<Persistable> dataStore;

    public DaoActionClient() {
        dataStore = DataStoreHolder.INSTANCE.getDataStore();
    }

    public void upsertActionClient(ActionClient actionClient) {
        dataStore.upsert(actionClient);
    }

    public Result<Client> getClientsByAction(Action action) {
        return dataStore.select(Client.class).join(ActionClient.class).on(Client.PASSPORT
                .eq(ActionClient.CLIENT_ID)).where(ActionClient.ACTION.eq(action)).get();
    }

    public Result<ActionClient> getActionClients(Action action) {
        return dataStore.select(ActionClient.class).where(ActionClient.ACTION.eq(action)).get();
    }

    public void deleteActioClient(ActionClient actionClient) {
        dataStore.delete(actionClient);
    }
}
