package caribehostal.caseroserver.controller;

import android.content.Context;

import caribehostal.caseroserver.dataaccess.DaoOwner;
import caribehostal.caseroserver.datamodel.Owner;
import caribehostal.caseroserver.notification.NotificationBar;

/**
 * Created by asio on 9/26/2017.
 */

public class SmsReceiverOwnerController {

    private String[] values;
    private Context context;
    private String cell;
    private static int UNREGISTERED = 0;
    private static String TITLE = "Registrar Propietario";
    private static String BIG_TEXT = "ENHORABUENA!!!! Un nuevo propietario quiere trabajar con Casero Móvil. Comienza por registrar sus datos.";

    public SmsReceiverOwnerController(String cell, String smsBody, Context context) {
        values = smsBody.split("#");
        this.context = context;
        this.cell = cell;
        insertOwner();
    }

    private void insertOwner() {
        DaoOwner daoOwner = new DaoOwner();
        if (daoOwner.getOwnerByCell(values[3]) == null) {
            Owner owner = new Owner();
            owner.setFullName(values[1]);
            owner.setCarnetId(values[2]);
            owner.setCell(cell);
            owner.setUser(values[3]);
            owner.setPassword(values[4]);
            owner.setAddress(values[5]);
            owner.setAddressDescription(values[6]);
            owner.setRegister(UNREGISTERED);
            daoOwner.upsertOwner(owner);
            notificOwnerRegisterPetition(1, values[1]);
        }
    }

    private void notificOwnerRegisterPetition(int id, String name) {
        NotificationBar notificationBar = new NotificationBar();
        notificationBar.notificRegisterOwner(context, id, TITLE, name, BIG_TEXT);
    }

}
