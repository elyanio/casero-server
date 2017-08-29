package caribehostal.caseroserver.dataaccess

import caribehostal.caseroserver.datamodel.Action
import caribehostal.caseroserver.datamodel.Owner
import caribehostal.caseroserver.datamodel.PaymentPeriod
import org.threeten.bp.LocalDate

/**
 * @author rainermf
 */

fun DaoAction.buildPayment(owner: Owner, startDate: LocalDate, endDate: LocalDate): PaymentPeriod {
    val actions = findActions(owner, startDate, endDate)
    return PaymentPeriod(
            startDate = startDate,
            endDate = endDate,
            owner = owner,
            actions = actions.groupBy(Action::getDateAction)
    )
}
