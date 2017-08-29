package caribehostal.caseroserver.datamodel

import caribehostal.caseroserver.dataaccess.DatabaseMock
import caribehostal.caseroserver.dataaccess.buildPayment
import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal.valueOf as decimal

/**
 * @author rainermf
 */
class DaoActionTest {

    @Test
    fun buildPayment() {
        val db = DatabaseMock()
        val dao = db.actionDao
        val payments = db.ownerDao.allOwners.map {
            dao.buildPayment(owner = it, startDate = db.startDate, endDate = db.endDate)
                    .payment(decimal(25L))
        }.toTypedArray()
        Assert.assertArrayEquals(arrayOf(decimal(50), decimal(50)), payments)
    }
}