package caribehostal.caseroserver.datamodel

import io.requery.converter.EnumOrdinalConverter

/**
* @author Fernando
*/
enum class ActionType {
    EDIT, INSERT
}

class ActionTypeConverter : EnumOrdinalConverter<ActionType>(ActionType::class.java)