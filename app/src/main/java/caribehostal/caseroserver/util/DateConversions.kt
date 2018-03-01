package caribehostal.caseroserver.util

import org.threeten.bp.LocalDate
import java.util.*

@Suppress("DEPRECATION")
inline fun Date.toLocalDate() = LocalDate.of(this.year + 1900, this.month + 1, this.date)
@Suppress("DEPRECATION")
inline fun LocalDate.toDate() = Date(this.year - 1900, this.monthValue - 1, this.dayOfMonth)