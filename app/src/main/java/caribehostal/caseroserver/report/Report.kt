package caribehostal.caseroserver.report

import caribehostal.caseroserver.dataaccess.DaoAction
import caribehostal.caseroserver.dataaccess.DaoOwner
import caribehostal.caseroserver.dataaccess.DaoClient
import caribehostal.caseroserver.dataaccess.buildPayment
import caribehostal.caseroserver.datamodel.Action
import caribehostal.caseroserver.datamodel.Owner
import caribehostal.caseroserver.datamodel.PaymentPeriod
import com.itextpdf.text.Document
import com.itextpdf.text.FontFactory
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import org.threeten.bp.LocalDate
import java.io.FileOutputStream
import java.math.BigDecimal

/**
 * @author rainermf
 */
class Report(
        val startDate: LocalDate,
        val endDate: LocalDate,
        val dest: String,
        val dairyPayment: BigDecimal,
        val actionDao: DaoAction = DaoAction(),
        val ownerDao: DaoOwner = DaoOwner(),
        val clientDao: DaoClient = DaoClient()
) {
    val doc = Document()

    fun createPdf() {
        PdfWriter.getInstance(doc, FileOutputStream(dest))
        doc.open()
        printOwners(ownerDao.getOwnersForPayingPeriod(startDate, endDate))
        doc.close()
    }

    fun printOwners(owners: Iterable<Owner>) {
        for (owner in owners) {
            doc.add(title(owner.fullName))
            val payment = actionDao.buildPayment(owner, startDate, endDate)
            printPayDays(payment)
            doc.add(heading("Total: ${payment.payment(dairyPayment)}"))
            doc.newPage()
        }
    }

    fun printPayDays(payment: PaymentPeriod) {
        for (date in payment.actions.keys) {
            doc.add(heading(date.toString()))
            val actions = payment.actions[date]
            if (actions != null) {
                printActions(actions)
            }
        }
    }

    fun printActions(actions: List<Action>) {
        for (action in actions) {
            doc.add(text("${action.checkIn} - ${action.checkOut}: ${clientDao.findClientsInAction(action).joinToString { it.passport }}"))
        }
    }

    private fun title(titleText: String): Paragraph {
        val title = Paragraph()
        with(title) {
            alignment = Paragraph.ALIGN_CENTER
            font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13f)
            spacingAfter = 12f
            add(titleText)
        }

        return title
    }

    private fun heading(text: String): Paragraph {
        val heading = Paragraph()
        with(heading) {
            font = FontFactory.getFont(FontFactory.HELVETICA, 12f)
            add(text)
        }
        return heading
    }

    private fun text(text: String): Paragraph {
        val paragraph = Paragraph()
        with(paragraph) {
            font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12f)
            add(text)
        }
        return paragraph
    }

}
