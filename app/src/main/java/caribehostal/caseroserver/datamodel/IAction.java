package caribehostal.caseroserver.datamodel;

import org.threeten.bp.LocalDate;

import caribehostal.appcasero.datamodel.ActionType;
import caribehostal.appcasero.datamodel.ActionTypeConverter;
import io.requery.Column;
import io.requery.Convert;
import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.ManyToOne;
import io.requery.OneToMany;
import io.requery.Persistable;
import io.requery.query.Result;

import static io.requery.PropertyNameStyle.FLUENT_BEAN;

/**
 * Created by Fernando on 16/08/2017.
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
    @Convert(LocalDateConverter.class)
    LocalDate getDateAction();

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
}
