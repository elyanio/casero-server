package caribehostal.caseroserver

/**
 * @author rainermf
 * *
 * @since 20/2/2017
 */
class CaseroServerApplication : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        caribehostal.caseroserver.CaseroServerApplication.Companion.instance = this
        com.jakewharton.threetenabp.AndroidThreeTen.init(this)
        java.util.Locale.setDefault(java.util.Locale("es"))
    }

    companion object {

        private var instance: caribehostal.caseroserver.CaseroServerApplication? = null

        fun instance(): caribehostal.caseroserver.CaseroServerApplication {
            return caribehostal.caseroserver.CaseroServerApplication.Companion.instance!!
        }
    }
}
