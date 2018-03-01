package caribehostal.caseroserver.datamodel

import io.requery.converter.EnumStringConverter

/**
 * @author Fernando
 */
enum class ActionState {
    PENDING, FINISH
}

class ActionStateConverter : EnumStringConverter<ActionState>(ActionState::class.java)