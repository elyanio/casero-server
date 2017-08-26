package caribehostal.caseroserver.util

import caribehostal.caseroserver.util.StringValidation.isCubanIdCard
import caribehostal.caseroserver.util.StringValidation.isCubanCellphone
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * @author rainermf
 */
class StringValidationTest {

    @Test
    fun isCubanCellphoneTest() {
        assertTrue(isCubanCellphone("53746802"))
        assertTrue(isCubanCellphone("+5353746802"))
        assertFalse(isCubanCellphone("+53537+6802"))
        assertFalse(isCubanCellphone("5700+890"))
    }

    @Test
    fun isCarnetTest() {
        assertTrue(isCubanIdCard("90122633288"))
        assertFalse(isCubanIdCard("90122b33288"))
        assertFalse(isCubanIdCard("9012233288"))
    }
}