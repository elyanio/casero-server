package caribehostal.caseroserver.view.report

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import caribehostal.caseroserver.R
import com.squareup.timessquare.CalendarPickerView.SelectionMode.RANGE
import kotlinx.android.synthetic.main.activity_report.*
import java.util.*

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        val today = Date()
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 1)
        calendar_view.init(today, nextYear.time)
                .withSelectedDate(today)
                .inMode(RANGE)
    }
}
