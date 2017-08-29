package caribehostal.caseroserver.dataaccess;

import org.threeten.bp.LocalDate;

import java.util.List;

import caribehostal.caseroserver.datamodel.Action;
import caribehostal.caseroserver.datamodel.Owner;
import io.requery.Persistable;
import io.requery.sql.EntityDataStore;

/**
 * @author asio
 */
public class DaoOwner {

    private EntityDataStore<Persistable> dataStore;

    public DaoOwner() {
        dataStore = DataStoreHolder.INSTANCE.getDataStore();
    }

    public void upsertOwner(Owner owner) {
        dataStore.upsert(owner);
    }

    public List<Owner> getAllOwners() {
        return dataStore.select(Owner.class).orderBy(Owner.FULL_NAME).get().toList();
    }

    public Owner getOwner(String cell) {
        return dataStore.select(Owner.class).where(Owner.CELL.eq(cell)).get().firstOrNull();
    }

    public List<Owner> getOwnersForPayingPeriod(LocalDate startDate, LocalDate endDate) {
        return dataStore.select(Owner.class)
                .join(Action.class).on(Action.OWNER_ID.eq(Owner.CARNET_ID))
                .where(Action.DATE_ACTION.between(startDate, endDate))
                .groupBy(Owner.CARNET_ID)
                .get()
                .toList();
    }

    public void remove(Owner owner) {
        dataStore.delete(owner);
    }
}
