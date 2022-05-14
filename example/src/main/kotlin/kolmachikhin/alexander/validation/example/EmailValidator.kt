package kolmachikhin.alexander.validation.example

import android.util.Patterns
import kolmachikhin.alexander.validation.Validator

class EmailValidator : Validator<String, EmailValidator.IncorrectReason>() {

    override suspend fun String.incorrectReason() = when {
        isEmpty() -> IncorrectReason.Empty()
        !matches(Patterns.EMAIL_ADDRESS.toRegex()) -> IncorrectReason.Pattern()
        else -> null
    }

    sealed class IncorrectReason {
        class Empty : IncorrectReason()
        class Pattern : IncorrectReason()
    }
}
