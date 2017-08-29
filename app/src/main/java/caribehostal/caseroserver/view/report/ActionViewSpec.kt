package caribehostal.caseroserver.view.report

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import caribehostal.caseroserver.dataaccess.Resources
import caribehostal.caseroserver.report.Report
import com.facebook.litho.ClickEvent
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.widget.Card
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaEdge
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.threeten.bp.LocalDate
import java.io.File
import java.math.BigDecimal

/**
 * @author rainermf
 */
@LayoutSpec
object ActionViewSpec {

    @OnCreateLayout @JvmStatic
    fun onCreateLayout(context: ComponentContext): ComponentLayout {
        return Column.create(context)
                .paddingDip(YogaEdge.ALL, 16)
                .child(Text.create(context)
                        .text("Rainer")
                        .textSizeSp(24F))
                .child(Text.create(context)
                        .text("Martïnez")
                        .textSizeSp(18F))
                .child(Card.create(context)
                        .cardBackgroundColor(Color.BLUE)
                        .content(
                                Text.create(context)
                                        .text("Reporte")
                                        .textSizeSp(18F)
                        ).withLayout()
                        .clickHandler(ActionView.onClick(context)))
                .build()
    }

    @OnEvent(ClickEvent::class) @JvmStatic
    fun onClick(context: ComponentContext) {
        makeText(context, "Elaborando el calendario...", LENGTH_LONG).show()
        doAsync {
            val file = createPdf()
            uiThread { displayPdf(context, file) }
        }
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
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.fromFile(file)))
        } catch (e: ActivityNotFoundException) {
            makeText(context, "No existe visor de pdf para visualizar el calendario.", LENGTH_LONG).show()
        }
    }
}