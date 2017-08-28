package caribehostal.caseroserver.view.report

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import caribehostal.caseroserver.R
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = ComponentContext(this)
        val view = ActionView.create(context).build()

        setContentView(LithoView.create(context, view))
    }
}
