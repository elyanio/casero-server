package caribehostal.caseroserver.controller;

import caribehostal.caseroserver.dataaccess.DaoOwner;
import caribehostal.caseroserver.datamodel.Owner;

/**
 * Created by asio on 9/26/2017.
 */

public class SmsReceiverOwnerController {

    private String[] values;
    private static int UNREGISTERED = 0;

    public SmsReceiverOwnerController(String cell, String smsBody) {
        values = smsBody.split("#");
        insertOwner();
    }

    private void insertOwner() {
        DaoOwner daoOwner = new DaoOwner();
        if (daoOwner.getOwnerByCell(values[3]) == null) {
            Owner owner = new Owner();
            owner.setFullName(values[1]);
            owner.setCarnetId(values[2]);
            owner.setCell(values[3]);
            owner.setUser(values[4]);
            owner.setPassword(values[5]);
            owner.setAddress(values[6]);
            owner.setAddressDescription(values[7]);
            owner.setRegister(UNREGISTERED);
            daoOwner.upsertOwner(owner);
        }
    }

}
