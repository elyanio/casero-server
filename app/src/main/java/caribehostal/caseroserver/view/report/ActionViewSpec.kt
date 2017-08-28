package caribehostal.caseroserver.view.report

import android.graphics.Color
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
import org.threeten.bp.LocalDate
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
//        Report(
//                startDate = LocalDate.of(2017, 1, 1),
//                endDate = LocalDate.of(2017, 12, 31),
//                dairyPayment = BigDecimal.valueOf(25L),
//                dest = Resources.resolve("reporte_casero.pdf").absolutePath
//        ).createPdf()
    }
}