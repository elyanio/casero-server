package caribehostal.appcasero.comunication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony.Sms.Intents.getMessagesFromIntent
import android.support.annotation.RequiresApi
import caribehostal.caseroserver.controller.SmsReceiverController
import caribehostal.caseroserver.controller.SmsReceiverOwnerController

/**
 * Created by asio on 8/17/2017.
 */
class SmsReceiver : BroadcastReceiver() {
    private val ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"
    private val ACTION_REGISTER_OWNER = "<"
//    private val ACTION_REGISTER_CLIENT = 2

    @RequiresApi(Build.VERSION_CODES.KITKAT)
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
            numberSender = numberSender.substring(numberSender.length - 8)
            getDataFromMessage(numberSender, messageBody.trim(), context)
        }
    }

    fun getDataFromMessage(numberSender: String, message: String, context: Context?) {
        val split = message.split("#")
        if (split[0].equals(ACTION_REGISTER_OWNER)) {
            SmsReceiverOwnerController(numberSender, message, context)
        } else {
            var smsController: SmsReceiverController = SmsReceiverController(numberSender, message, context)
            if (smsController.checkEmisor(numberSender)) {
                if (smsController.createObjects())
                    smsController.upsertElements()
            }
        }
    }
}