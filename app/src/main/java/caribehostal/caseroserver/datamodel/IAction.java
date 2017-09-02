package caribehostal.caseroserver.datamodel;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

import io.requery.Column;
import io.requery.Convert;
import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.ManyToOne;
import io.requery.Persistable;

import static io.requery.PropertyNameStyle.FLUENT_BEAN;

/**
 * @author Fernando
 */
@Entity(propertyNameStyle = FLUENT_BEAN)
public interface IAction extends Persistable {

    @Key
    @Generated
    @Column(nullable = false)
    int getId();

    @Column(nullable = false)
    String getPetitionOwnerId();

    @Column(nullable = false)
    @ManyToOne
    Owner getOwner();

    @Column(nullable = false)
    @Convert(LocalDateTimeConverter.class)
    LocalDateTime getReceiveDate();

    @Column(nullable = false)
    @Convert(LocalDateConverter.class)
    LocalDate getCheckIn();

    @Column(nullable = false)
    @Convert(LocalDateConverter.class)
    LocalDate getCheckOut();

    @Column(nullable = false)
    @Convert(ActionTypeConverter.class)
    ActionType getActionType();

    @Column(nullable = false)
    @Convert(ActionStateConverter.class)
    ActionState getActionState();

    @Column(nullable = false)
    @Convert(LocalDateTimeConverter.class)
    LocalDateTime getProcessedDate();
}
