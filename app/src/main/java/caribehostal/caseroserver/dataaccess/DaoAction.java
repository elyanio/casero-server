package caribehostal.caseroserver.dataaccess;

import org.threeten.bp.LocalDate;

import java.util.List;

import caribehostal.caseroserver.datamodel.Action;
import caribehostal.caseroserver.datamodel.Owner;
import io.requery.Persistable;
import io.requery.query.Result;
import io.requery.sql.EntityDataStore;

import static caribehostal.caseroserver.datamodel.Owner.CARNET_ID;

/**
 * @author asio
 */
public class DaoAction {
    private EntityDataStore<Persistable> dataStore;

    public DaoAction() {
        dataStore = DataStoreHolder.INSTANCE.getDataStore();
    }

    public void upsertAction(Action action) {
        dataStore.upsert(action);
    }

    public boolean existAction(Action action) {
        Action act = dataStore.select(Action.class).join(Owner.class).on(Action.OWNER_ID.eq(CARNET_ID))
                .where(Action.PETITION_OWNER_ID.eq(action.getPetitionOwnerId()))
                .and(Owner.CELL.eq(action.getOwner().getCell())).get().firstOrNull();
        return act != null;
    }

    public Result<Action> getAllActions() {
        return dataStore.select(Action.class).orderBy(Action.DATE_ACTION).get();
    }

    public Action getActionByPetitionId(int idPetition) {
        return dataStore.select(Action.class)
                .where(Action.ID.eq(idPetition))
                .get().first();
    }

    public List<Action> findActions(Owner owner, LocalDate startDate, LocalDate endDate) {
        return dataStore.select(Action.class)
                .where(Action.OWNER.eq(owner))
                .and(Action.DATE_ACTION.between(startDate, endDate))
                .orderBy(Action.DATE_ACTION)
                .get()
                .toList();
    }
}
