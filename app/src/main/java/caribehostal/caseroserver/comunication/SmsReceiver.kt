package caribehostal.appcasero.comunication

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony.Sms.Intents.getMessagesFromIntent
import caribehostal.caseroserver.controller.SmsReceiverController

/**
 * Created by asio on 8/17/2017.
 */
class SmsReceiver : BroadcastReceiver() {
    private val ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"

    @TargetApi(Build.VERSION_CODES.KITKAT)
    override fun onReceive(context: Context?, intent: Intent?) {

        val action = intent!!.action
        if (action == ACTION_SMS_RECEIVED) {
            val msgs = getMessagesFromIntent(intent)
            var numberSender: String = ""
            var messageBody: String = ""
            if (msgs != null) {
                for (msg in msgs) {
                    numberSender = msg.originatingAddress
                    messageBody += msg.messageBody
                }
            }
            getDataFromMessage(numberSender, messageBody)
        }
    }

    //petitionOwner#ActionState#1234456#123446#876544#1234456#123345345435#locaclDate#localDate
    fun getDataFromMessage(numberSender: String, message: String) {
        var smsController: SmsReceiverController = SmsReceiverController(numberSender, message);

        if (smsController.checkEmisor(numberSender)) {

        }
    }
}