package caribehostal.caseroserver.report

import caribehostal.caseroserver.dataaccess.DatabaseMock
import org.junit.Test
import java.math.BigDecimal
import org.threeten.bp.LocalDate.of as date

/**
 * @author rainermf
 */
class ReportTest {

    @Test
    fun createPdfTest() {
        val db = DatabaseMock()

        Report(startDate = db.startDate.toLocalDate(),
                endDate = db.endDate.toLocalDate(),
                dest = "report.pdf",
                dairyPayment = BigDecimal.valueOf(25L),
                actionDao = db.actionDao,
                ownerDao = db.ownerDao,
                clientDao = db.clientDao
        ).createPdf()
    }

}
