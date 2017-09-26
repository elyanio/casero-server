package caribehostal.caseroserver.dataaccess;

import org.threeten.bp.LocalDateTime;

import java.util.List;

import caribehostal.caseroserver.datamodel.Action;
import caribehostal.caseroserver.datamodel.Owner;
import io.requery.Persistable;
import io.requery.query.Result;
import io.requery.sql.EntityDataStore;

/**
 * @author asio
 */
public class DaoOwner {
    private static int REGISTERED = 1;
    private static int UNREGISTERED = 0;

    private EntityDataStore<Persistable> dataStore;

    public DaoOwner() {
        dataStore = DataStoreHolder.INSTANCE.getDataStore();
    }

    public void upsertOwner(Owner owner) {
        dataStore.upsert(owner);
    }

    public List<Owner> getAllOwners() {
        return dataStore.select(Owner.class).where(Owner.REGISTER.eq(REGISTERED)).orderBy(Owner.FULL_NAME).get().toList();
    }

    public List<Owner> getAllUnregisteredOwners() {
        return dataStore.select(Owner.class).where(Owner.REGISTER.eq(UNREGISTERED)).orderBy(Owner.FULL_NAME).get().toList();
    }

    public Owner getOwnerByCell(String cell) {
        return dataStore.select(Owner.class).where(Owner.CELL.eq(cell)).and(Owner.REGISTER.eq(REGISTERED)).get().firstOrNull();
    }

    public List<Owner> getOwnersForPayingPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return dataStore.select(Owner.class)
                .join(Action.class).on(Action.OWNER_ID.eq(Owner.CARNET_ID))
                .where(Action.PROCESSED_DATE.between(startDate, endDate)).and(Owner.REGISTER.eq(REGISTERED))
                .groupBy(Owner.CARNET_ID)
                .get()
                .toList();
    }

    public void remove(Owner owner) {
        dataStore.delete(owner);
    }
}
