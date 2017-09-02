package caribehostal.caseroserver.dataaccess;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

import java.util.List;

import caribehostal.caseroserver.datamodel.Action;
import caribehostal.caseroserver.datamodel.ActionState;
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
        return dataStore.select(Action.class).orderBy(Action.RECEIVE_DATE).get();
    }

    public Action getActionByPetitionId(int idPetition) {
        return dataStore.select(Action.class)
                .where(Action.ID.eq(idPetition))
                .get().first();
    }

    public List<Action> findActions(Owner owner, LocalDateTime startDate, LocalDateTime endDate) {
        return dataStore.select(Action.class)
                .where(Action.OWNER.eq(owner))
                .and(Action.RECEIVE_DATE.between(startDate, endDate))
                .orderBy(Action.RECEIVE_DATE)
                .get()
                .toList();
    }

    public Result<Action> getPendingActions() {
        return dataStore.select(Action.class)
                .where(Action.ACTION_STATE.eq(ActionState.PENDING))
                .orderBy(Action.RECEIVE_DATE).get();
    }

    public Result<Action> getFinishActions() {
        return dataStore.select(Action.class)
                .where(Action.ACTION_STATE.eq(ActionState.FINISH))
                .orderBy(Action.RECEIVE_DATE).get();
    }

    public void removeAction(Action action) {
        dataStore.delete(action);
    }

    public void updateAction(Action action) {
        dataStore.update(Action.class).set(Action.RECEIVE_DATE, action.getReceiveDate())
                .set(Action.CHECK_IN, action.getCheckIn())
                .set(Action.CHECK_OUT, action.getCheckOut())
                .where(Action.PETITION_OWNER_ID.eq(action.getPetitionOwnerId())).and(Action.OWNER.eq(action.getOwner())).get().value();
    }

    public Action getAction(String ownerPetition, String ownerId) {
        return dataStore.select(Action.class).where(Action.PETITION_OWNER_ID
                .eq(ownerPetition).and(Action.OWNER_ID.eq(ownerId))).get().first();
    }
}
