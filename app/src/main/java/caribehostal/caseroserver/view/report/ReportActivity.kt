package caribehostal.caseroserver.view.report

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.OnClick
import caribehostal.caseroserver.R
import caribehostal.caseroserver.util.toDate
import caribehostal.caseroserver.util.toLocalDate
import com.squareup.timessquare.CalendarPickerView.SelectionMode.RANGE
import kotlinx.android.synthetic.main.activity_report.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.threeten.bp.LocalDate

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        val today = LocalDate.now()
        val start = today.minusYears(2)
        val end = today.plusYears(2)
        calendar_view.init(start.toDate(), end.toDate())
                .withSelectedDate(today.toDate())
                .inMode(RANGE)
        create_report_button.onClick { createReport() }
    }

    fun createReport() {
        toast("Elaborando el calendario...")
        val selection = calendar_view.selectedDates
        val start = selection.first().toLocalDate()
        val end = selection.last().toLocalDate()
        doAsync {
            val file = ActionViewSpec.createPdf(start, end)
            uiThread {
                ActionViewSpec.displayPdf(this@ReportActivity, file)
            }
        }
    }
}
