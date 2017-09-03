package caribehostal.caseroserver.controller;

import android.content.Context;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import caribehostal.caseroserver.dataaccess.DaoAction;
import caribehostal.caseroserver.dataaccess.DaoActionClient;
import caribehostal.caseroserver.dataaccess.DaoClient;
import caribehostal.caseroserver.dataaccess.DaoOwner;
import caribehostal.caseroserver.datamodel.Action;
import caribehostal.caseroserver.datamodel.ActionClient;
import caribehostal.caseroserver.datamodel.ActionState;
import caribehostal.caseroserver.datamodel.ActionType;
import caribehostal.caseroserver.datamodel.ActionTypeConverter;
import caribehostal.caseroserver.datamodel.Client;
import caribehostal.caseroserver.datamodel.LocalDateConverter;
import caribehostal.caseroserver.datamodel.Owner;
import caribehostal.caseroserver.notification.NotificationBar;

/**
 * Created by asio on 8/29/2017.
 */

public class SmsReceiverController {

    private String cell;
    private String[] values;
    private String ownerPetition;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private ActionType actionType;
    private ActionState actionState;
    private List<Client> clients;
    private List<ActionClient> actionClients;
    private Owner owner;
    private Action action;
    private Context context;

    public SmsReceiverController(String cell, String string, Context context) {
        this.cell = cell;
        clients = new ArrayList<>();
        actionClients = new ArrayList<>();
        this.context = context;
        this.values = string.split("#");
    }

    public boolean checkEmisor(String cell) {
        DaoOwner daoOwner = new DaoOwner();
        return daoOwner.getOwnerByCell(cell) != null;
    }

    public void createObjects() {
        findOwner(cell);
        findDates();
        findOwnerPetition();
        findActionType();
        findActionState();
        createAction();
        findClients();
        createActionClients();
    }

    private void updateAction() {
        DaoAction daoAction = new DaoAction();
        daoAction.updateAction(action);
    }

    private void findOwner(String cell) {
        DaoOwner daoOwner = new DaoOwner();
        owner = daoOwner.getOwnerByCell(cell);
    }

    private void findDates() {
        checkOut = new LocalDateConverter().convertToMapped(LocalDate.class, values[values.length - 1]);
        checkIn = new LocalDateConverter().convertToMapped(LocalDate.class, values[values.length - 2]);
    }

    private void findOwnerPetition() {
        ownerPetition = values[0];
    }

    private void findActionType() {
        actionType = new ActionTypeConverter().convertToMapped(ActionType.class, Integer.parseInt(values[1]));
    }

    private void findActionState() {
        actionState = ActionState.PENDING;
    }

    private void createAction() {
        action = new Action().setActionType(actionType).setOwner(owner)
                .setActionState(actionState).setPetitionOwnerId(ownerPetition)
                .setCheckIn(checkIn).setCheckOut(checkOut).setReceiveDate(LocalDateTime.now());
    }

    private void findClients() {
        for (int i = 2; i < values.length - 2; i++) {
            clients.add(new Client().setPassport(values[i]));
        }
    }

    private void createActionClients() {
        for (Client client : clients) {
            actionClients.add(new ActionClient().setAction(action).setClient(client));
        }
    }

    private void insertClients() {
        DaoClient daoClient = new DaoClient();
        for (Client client : clients) {
            daoClient.upsertClient(client);
        }
    }

    private void insertAction() {
        DaoAction daoAction = new DaoAction();
        daoAction.upsertAction(action);
    }

    private void insertActionClient() {
        DaoActionClient daoActionClient = new DaoActionClient();
        for (ActionClient actionClient : actionClients) {
            daoActionClient.upsertActionClient(actionClient);
        }
    }

    public void upsertElements() {
        if (actionType.equals(ActionType.INSERT)) {
            insertAction();
            insertClients();
            insertActionClient();
            String bigText = createMessage("Se ha notificado, una nueva petición");
            createNotification("Nueva Petición", action.getId(), owner.getFullName(), action.getPetitionOwnerId(), bigText);
        } else if (actionType.equals(ActionType.EDIT)) {
            updateAction();
            getMyAction();
            removeActionClients();
            insertClients();
            updateActionClients();
            String bigText = createMessage("Se ha notificado, una actualización de petición");
            createNotification("Actualizar Petición", action.getId(), owner.getFullName(), action.getPetitionOwnerId(), bigText);
        }
    }

    private String createMessage(String encabezado) {
        String checkIn = new LocalDateConverter().convertToPersisted(action.getCheckIn());
        String checkOut = new LocalDateConverter().convertToPersisted(action.getCheckOut());
        String ownerName = action.getOwner().getFullName();
        String cellOwner = action.getOwner().getCell();
        String petitionCode = action.getPetitionOwnerId();

        return encabezado + " desde " + checkIn + " hasta " + checkOut
                + " a nombre de " + ownerName + " con celular " + cellOwner
                + " y código de petición " + petitionCode;

    }

    private void getMyAction() {
        DaoAction daoAction = new DaoAction();
        action = daoAction.getAction(action.getPetitionOwnerId(), action.getOwner().getCarnetId());
    }

    private void updateActionClients() {
        DaoActionClient daoActionClient = new DaoActionClient();
        actionClients = new ArrayList<>();
        for (Client client : clients) {
            daoActionClient.upsertActionClient(new ActionClient().setClient(client).setAction(action));
        }
    }

    private void removeActionClients() {
        DaoActionClient daoActionClient = new DaoActionClient();
        List<ActionClient> actionClients = daoActionClient.getActionClients(action).toList();
        for (ActionClient actionClient : actionClients) {
            daoActionClient.deleteActioClient(actionClient);
        }
    }

    private void createNotification(String title, int id, String ownerName, String ownerPetition, String bigText) {
        NotificationBar notificationBar = new NotificationBar();
        notificationBar.createNotification(context, id, title, ownerName, ownerPetition, bigText);
    }
}
