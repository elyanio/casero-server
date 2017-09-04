package caribehostal.caseroserver.view.report

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import caribehostal.caseroserver.dataaccess.Resources
import caribehostal.caseroserver.report.Report
import caribehostal.caseroserver.settings.Settings
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import java.io.File
import java.math.BigDecimal

/**
 * @author rainermf
 */
object ActionViewSpec {

    fun createPdf(startDate: LocalDate, endDate: LocalDate): File {
        val file = Resources.resolve("reporte_casero${System.currentTimeMillis()}.pdf")
        Report(
                startDate = startDate,
                endDate = endDate,
                dairyPayment = Settings.pricePerDay(),
                dest = file.absolutePath
        ).createPdf()
        return file
    }

    fun displayPdf(context: Context, file: File) {
        try {
            context.startActivity(Intent(ACTION_VIEW, Uri.fromFile(file)))
        } catch (e: ActivityNotFoundException) {
            context.longToast("No existe visor de pdf para visualizar el calendario.")
        }
    }
}
