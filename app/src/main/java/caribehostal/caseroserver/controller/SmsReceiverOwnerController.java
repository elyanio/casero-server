package caribehostal.caseroserver.controller;

/**
 * Created by asio on 9/26/2017.
 */

public class SmsReceiverOwnerController {

    private String[] values;

    public SmsReceiverOwnerController(String cell, String smsBody) {
        values = smsBody.split("#");
    }



}
