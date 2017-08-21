package caribehostal.appcasero.comunication

import android.telephony.SmsManager

/**
 * Created by asio on 8/17/2017.
 */
class SmsSender {

    fun enviar_mensaje(numero: String, mensaje: String) {
        val sms = SmsManager.getDefault()
        sms.sendTextMessage(numero, null, mensaje, null, null)
    }

    fun enviar_mensaje(numbers: List<String>, mensaje: String) {
        val sms = SmsManager.getDefault()
        if (numbers.size != 0) {
            for (number in numbers) {
                sms.sendTextMessage(number, null, mensaje, null, null)
            }
        }
    }
}