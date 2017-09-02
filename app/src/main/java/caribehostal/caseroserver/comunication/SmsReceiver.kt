package caribehostal.appcasero.comunication

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony.Sms.Intents.getMessagesFromIntent
import android.support.annotation.RequiresApi
import android.util.Log
import caribehostal.caseroserver.controller.SmsReceiverController

/**
 * Created by asio on 8/17/2017.
 */
class SmsReceiver : BroadcastReceiver() {
    private val ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"


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
            getDataFromMessage(numberSender, messageBody, context)
        }
    }

    fun getDataFromMessage(numberSender: String, message: String, context: Context?) {
        var smsController: SmsReceiverController = SmsReceiverController(numberSender, message, context);

        if (smsController.checkEmisor(numberSender.substring(numberSender.length - 8))) {
            smsController.createObjects(numberSender, message)
            smsController.upsertElements()
        }
    }
}