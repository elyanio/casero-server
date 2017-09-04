package caribehostal.caseroserver.view.report

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import caribehostal.caseroserver.dataaccess.Resources
import caribehostal.caseroserver.report.Report
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.threeten.bp.LocalDate
import java.io.File
import java.math.BigDecimal

/**
 * @author rainermf
 */
object ActionViewSpec {

    @JvmStatic
    fun onCreateLayout(context: Context) {

    }

    @JvmStatic
    fun onClick(context: Context) {
        context.toast("Elaborando el calendario...")
        val file = createPdf()
        displayPdf(context, file)
    }

    fun createPdf(): File {
        val file = Resources.resolve("reporte_casero${System.currentTimeMillis()}.pdf")
        Report(
                startDate = LocalDate.of(2017, 1, 1),
                endDate = LocalDate.of(2017, 12, 31),
                dairyPayment = BigDecimal.valueOf(25L),
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