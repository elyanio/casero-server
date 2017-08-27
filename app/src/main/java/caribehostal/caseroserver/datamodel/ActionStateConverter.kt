package caribehostal.caseroserver.datamodel

import io.requery.converter.EnumStringConverter

/**
 * Created by Fernando on 21/08/2017.
 */
class ActionStateConverter: EnumStringConverter<ActionState>(ActionState::class.java) {
}