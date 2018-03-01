package caribehostal.caseroserver.dataaccess

import caribehostal.caseroserver.datamodel.Action
import caribehostal.caseroserver.datamodel.Owner
import caribehostal.caseroserver.datamodel.PaymentPeriod
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

/**
 * @author rainermf
 */

fun DaoAction.buildPayment(owner: Owner, startDate: LocalDateTime, endDate: LocalDateTime): PaymentPeriod {
    val actions = findActions(owner, startDate, endDate)
    return PaymentPeriod(
            startDate = startDate,
            endDate = endDate,
            owner = owner,
            actions = actions.groupBy(Action::getReceiveDate)
    )
}
