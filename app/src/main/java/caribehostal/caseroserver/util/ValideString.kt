package caribehostal.caseroserver.util

/**
 * Created by Fernando on 22/08/2017.
 */

object ValideString {
    @JvmStatic fun isPhone(cell: String): Boolean {
        return cell.length == 8 && cell[0] == '5'
    }

    @JvmStatic fun isCarnet(cell: String): Boolean {
        return cell.length == 11
    }
}
