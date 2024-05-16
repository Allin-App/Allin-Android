package fr.iut.alldev.allin.ext

import android.util.Patterns
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import fr.iut.alldev.allin.R

const val ALLOWED_SYMBOLS = "~`!@#\$%^&*()_-+={[}]|\\:;\"'<,>.?/"

sealed class FieldErrorState(
    private val messageId: Int? = null,
    private vararg val messageArgs: Any,
) {
    data object NoError : FieldErrorState()

    data object Mandatory : FieldErrorState(R.string.field_error_mandatory)


    data class TooShort(val fieldName: String, val minChar: Int) :
        FieldErrorState(R.string.field_error_too_short, fieldName, minChar)

    data class BadFormat(val fieldName: String, val format: String) :
        FieldErrorState(R.string.field_error_bad_format, fieldName, format)

    data object NotIdentical : FieldErrorState(R.string.field_error_not_identical)

    data class NoSpecialCharacter(val fieldName: String, val characters: String = ALLOWED_SYMBOLS) :
        FieldErrorState(R.string.field_error_no_special_character, fieldName, characters)

    data class AlreadyUsed(val value: String) :
        FieldErrorState(R.string.field_error_already_used, value)

    data class PastDate(val fieldName: String) :
        FieldErrorState(R.string.field_error_past_date, fieldName)

    data class DateOrder(val fieldName1: String, val fieldName2: String) :
        FieldErrorState(R.string.field_error_date_order, fieldName1, fieldName2)


    @Composable
    fun errorResource() = stringResourceOrNull(id = messageId, *messageArgs)
}

fun String.isEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.containsCharacter(characters: CharSequence): Boolean {
    var contains = false
    characters.forEach {
        if (this.contains(it)) {
            contains = true
            return@forEach
        }
    }
    return contains
}


@Composable
fun stringResourceOrNull(@StringRes id: Int?, vararg args: Any) = id?.let {
    stringResource(id = id, *args)
}

@Composable
fun stringResourceOrNull(@StringRes id: Int?) = id?.let {
    stringResource(id = id)
}
