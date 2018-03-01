package caribehostal.caseroserver.datamodel

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import java.math.BigDecimal

/**
 * @author rainermf
 */
data class PaymentPeriod(
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val owner: Owner,
        val actions: Map<LocalDateTime, List<Action>>
) {
    private val days by lazy { BigDecimal.valueOf(actions.keys.size.toLong()) }
    fun payment(dairyPayment: BigDecimal) = dairyPayment * days
}