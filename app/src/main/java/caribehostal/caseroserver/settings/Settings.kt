package caribehostal.caseroserver.settings

import android.content.SharedPreferences
import android.preference.PreferenceManager
import caribehostal.caseroserver.CaseroServerApplication
import java.math.BigDecimal

/**
 * @author rainermf
 */
object Settings {

    fun prefs(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(CaseroServerApplication.instance())
    fun pricePerDay(): BigDecimal = BigDecimal(prefs().getString("price_per_day", "10"))
}