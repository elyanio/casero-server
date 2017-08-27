package caribehostal.caseroserver.datamodel

import io.requery.converter.EnumOrdinalConverter

/**
* @author Fernando
*/
enum class ActionState {
    PENDING, FINISH
}

class ActionStateConverter: EnumOrdinalConverter<ActionState>(ActionState::class.java)