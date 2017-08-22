package caribehostal.caseroserver.util

/**
 * Created by Fernando on 22/08/2017.
 */

object ValideString {
    fun isPhone(cell: String): Boolean {
        if (cell.length == 8 && cell[0] == '5') {
            return true
        } else {
            return false
        }
    }
}
