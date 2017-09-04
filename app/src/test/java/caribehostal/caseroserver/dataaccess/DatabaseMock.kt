package caribehostal.caseroserver.dataaccess

import caribehostal.caseroserver.datamodel.Action
import caribehostal.caseroserver.datamodel.Client
import caribehostal.caseroserver.datamodel.Owner
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime

/**
 * @author rainermf
 */
class DatabaseMock {

    val startDate: LocalDateTime = LocalDateTime.of(LocalDate.of(2015, 10, 2), LocalTime.MIN)
    val endDate: LocalDateTime = LocalDateTime.of(LocalDate.of(2015, 10, 12), LocalTime.MAX)
    val ownerDao: DaoOwner = mock(DaoOwner::class.java)
    val actionDao: DaoAction = mock(DaoAction::class.java)
    val clientDao: DaoClient = mock(DaoClient::class.java)

    init {
        val owner1 = Owner().setCarnetId("90122633288").setFullName("Rainer Martinez")
        val owner2 = Owner().setCarnetId("90122636040").setFullName("Yanier Alfonso")

        given(ownerDao.getOwnersForPayingPeriod(startDate, endDate))
                .willReturn(listOf(owner1, owner2))
        given(ownerDao.allOwners).willReturn(listOf(owner1, owner2))
        val action1 = Action()
                .setReceiveDate(LocalDateTime.of(2015, 10, 2, 0, 0))
                .setProcessedDate(LocalDateTime.of(2015, 10, 2, 0, 0))
                .setCheckIn(LocalDate.of(2015, 11, 3))
                .setCheckOut(LocalDate.of(2015, 11, 7))
        val action2 = Action()
                .setReceiveDate(LocalDateTime.of(2015, 10, 3, 0, 0))
                .setProcessedDate(LocalDateTime.of(2015, 10, 3, 0, 0))
                .setCheckIn(LocalDate.of(2016, 1, 7))
                .setCheckOut(LocalDate.of(2016, 1, 10))
        val action3 = Action()
                .setReceiveDate(LocalDateTime.of(2015, 10, 2, 0, 0))
                .setProcessedDate(LocalDateTime.of(2015, 10, 2, 0, 0))
                .setCheckIn(LocalDate.of(2015, 12, 1))
                .setCheckOut(LocalDate.of(2015, 12, 7))
        val action4 = Action()
                .setReceiveDate(LocalDateTime.of(2015, 10, 5, 0, 0))
                .setProcessedDate(LocalDateTime.of(2015, 10, 5, 0, 0))
                .setCheckIn(LocalDate.of(2015, 12, 2))
                .setCheckOut(LocalDate.of(2015, 12, 5))
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
    }
}
