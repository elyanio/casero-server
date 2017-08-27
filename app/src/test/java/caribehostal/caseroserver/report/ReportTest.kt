package caribehostal.caseroserver.report

import caribehostal.caseroserver.dataaccess.DaoAction
import caribehostal.caseroserver.dataaccess.DaoClient
import caribehostal.caseroserver.dataaccess.DaoOwner
import caribehostal.caseroserver.datamodel.Action
import caribehostal.caseroserver.datamodel.Client
import caribehostal.caseroserver.datamodel.Owner
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import java.math.BigDecimal
import org.threeten.bp.LocalDate.of as date

/**
 * @author rainermf
 */
class ReportTest {

    @Test
    fun createPdfTest() {
        val ownerDao = Mockito.mock(DaoOwner::class.java)
        val actionDao = Mockito.mock(DaoAction::class.java)
        val clientDao = Mockito.mock(DaoClient::class.java)

        val owner1 = Owner().setCarnetId("90122633288").setFullName("Rainer Martinez")
        val owner2 = Owner().setCarnetId("90122636040").setFullName("Yanier Alfonso")
        val startDate = date(2015, 10, 2)
        val endDate = date(2015, 10, 12)
        given(ownerDao.getOwnersForPayingPeriod(startDate, endDate))
                .willReturn(listOf(owner1, owner2))
        val action1 = Action()
                .setDateAction(date(2015, 10, 2))
                .setCheckIn(date(2015, 11, 3))
                .setCheckOut(date(2015, 11, 7))
        val action2 = Action()
                .setDateAction(date(2015, 10, 3))
                .setCheckIn(date(2016, 1, 7))
                .setCheckOut(date(2016, 1, 10))
        val action3 = Action()
                .setDateAction(date(2015, 10, 2))
                .setCheckIn(date(2015, 12, 1))
                .setCheckOut(date(2015, 12, 7))
        val action4 = Action()
                .setDateAction(date(2015, 10, 5))
                .setCheckIn(date(2015, 12, 2))
                .setCheckOut(date(2015, 12, 5))
        given(actionDao.findActions(owner1, startDate, endDate))
                .willReturn(listOf(action1, action2))
        given(actionDao.findActions(owner2, startDate, endDate))
                .willReturn(listOf(action3, action4))

        val client1 = Client().setPassport("98349384934983")
        val client2 = Client().setPassport("kjd9f030940394")
        val client3 = Client().setPassport("kdjfi989893434")
        val client4 = Client().setPassport("dfkfjkm,m89dfd")
        val client5 = Client().setPassport("djfh767245451112")
        val client6 = Client().setPassport("090909283828CUB")

        given(clientDao.findClientsInAction(action1))
                .willReturn(listOf(client1, client2))
        given(clientDao.findClientsInAction(action2))
                .willReturn(listOf(client3))
        given(clientDao.findClientsInAction(action3))
                .willReturn(listOf(client4))
        given(clientDao.findClientsInAction(action4))
                .willReturn(listOf(client5, client6))

        Report(startDate = startDate,
                endDate = endDate,
                dest = "report.pdf",
                pricePerDay = BigDecimal.valueOf(25L),
                actionDao = actionDao,
                ownerDao = ownerDao,
                clientDao = clientDao
        ).createPdf()
    }

}