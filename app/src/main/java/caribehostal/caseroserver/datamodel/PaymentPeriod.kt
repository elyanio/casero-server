package caribehostal.caseroserver.datamodel

import org.threeten.bp.LocalDate
import java.math.BigDecimal

/**
 * @author rainermf
 */
data class PaymentPeriod(
        val startDate: LocalDate,
        val endDate: LocalDate,
        val owner: Owner,
        val actions: Map<LocalDate, List<Action>>
) {
    private val days by lazy { BigDecimal.valueOf(actions.keys.size.toLong()) }
    fun payment(dairyPayment: BigDecimal) = dairyPayment * days
}