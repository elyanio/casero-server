package caribehostal.caseroserver.util

/**
* @author Fernando
*/
object StringValidation {

    private val cubanCellphoneRegex = "(\\+53)?\\d{8}".toRegex()
    private val cubanIdCardRegex = "\\d{11}".toRegex()

    @JvmStatic fun isCubanCellphone(cell: String): Boolean {
        return cell.matches(cubanCellphoneRegex)
    }

    @JvmStatic fun isCubanIdCard(cell: String): Boolean {
        return cell.matches(cubanIdCardRegex)
    }
}
