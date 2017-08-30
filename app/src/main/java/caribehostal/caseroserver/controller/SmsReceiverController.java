package caribehostal.caseroserver.controller;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import caribehostal.caseroserver.dataaccess.DaoOwner;
import caribehostal.caseroserver.datamodel.Action;
import caribehostal.caseroserver.datamodel.ActionClient;
import caribehostal.caseroserver.datamodel.ActionState;
import caribehostal.caseroserver.datamodel.ActionType;
import caribehostal.caseroserver.datamodel.ActionTypeConverter;
import caribehostal.caseroserver.datamodel.Client;
import caribehostal.caseroserver.datamodel.LocalDateConverter;
import caribehostal.caseroserver.datamodel.Owner;

/**
 * Created by asio on 8/29/2017.
 */

public class SmsReceiverController {

    private String ownerPetition;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private ActionType actionType;
    private ActionState actionState;
    private List<Client> clients;
    private List<ActionClient> actionClients;
    private Owner owner;
    private Action action;

    public SmsReceiverController(String cell, String values) {
        clients = new ArrayList<>();
        actionClients = new ArrayList<>();
        findOwner(cell);
        findDates(values);
        findOwnerPetition(values);
        findActionType(values);
        findActionState(values);
        createAction();
        findClients(values);
    }

    public boolean checkEmisor(String cell) {
        DaoOwner daoOwner = new DaoOwner();
        return !daoOwner.getOwnerByCell(cell).equals(null);
    }

    // petitionCode#ActionType#passaport1#passaportX#CheckInDate#CheckOutDate
    public void createObjects(String cell, String values) {
        if (actionType.equals(ActionType.INSERT)) {
            createActionClients();
        } else {

        }
    }

    private void findOwner(String cell) {
        DaoOwner daoOwner = new DaoOwner();
        owner = daoOwner.getOwnerByCell(cell);
    }

    private void findDates(String values) {
        String[] split = values.split("#");
        checkIn = new LocalDateConverter().convertToMapped(LocalDate.class, split[split.length - 1]);
        checkOut = new LocalDateConverter().convertToMapped(LocalDate.class, split[split.length - 2]);
    }

    private void findOwnerPetition(String values) {
        String[] split = values.split("#");
        ownerPetition = split[0];
    }

    private void findActionType(String values) {
        String[] split = values.split("#");
        actionType = new ActionTypeConverter().convertToMapped(ActionType.class, Integer.parseInt(split[1]));
    }

    private void findActionState(String values) {
        actionState = ActionState.PENDING;
    }

    private void createAction() {
        action = new Action().setActionType(actionType).setOwner(owner)
                .setActionState(actionState).setPetitionOwnerId(ownerPetition)
                .setCheckIn(checkIn).setCheckOut(checkOut).setDateAction(LocalDate.now());
    }

    private void findClients(String values) {
        String[] split = values.split("#");
        for (int i = 2; i < split.length - 2; i++) {
            clients.add(new Client().setPassport(split[i]));
        }
    }

    private void createActionClients() {
        for (Client client : clients) {
            actionClients.add(new ActionClient().setAction(action).setClient(client));
        }
    }
}
