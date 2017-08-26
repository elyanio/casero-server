package caribehostal.caseroserver.util

/**
* @author Fernando
*/
object StringValidation {

    private val cubanCellphoneRegex = "(\\+53)?\\d{8}".toRegex()
    private val cubanIdCardRegex = "\\d{11}".toRegex()

    @JvmStatic fun isCubanCellphone(cell: CharSequence): Boolean {
        return cell.matches(cubanCellphoneRegex)
    }

    @JvmStatic fun isCubanIdCard(cell: CharSequence): Boolean {
        return cell.matches(cubanIdCardRegex)
    }
}
