package kolmachikhin.alexander.validation.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kolmachikhin.alexander.validation.Validated
import kotlinx.coroutines.flow.*

class EmailValidationViewModel(
    private val emailValidator: EmailValidator
) : ViewModel() {

    private val emailState = MutableStateFlow("")
    private val validatedEmailState = emailState.map {
        emailValidator.validate(it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val state = combine(
        emailState,
        validatedEmailState
    ) { email, validatedEmail ->
        State(
            email,
            validatedEmail
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun updateEmail(email: String) {
        emailState.value = email
    }

    data class State(
        val email: String,
        val validatedEmail: Validated<String, EmailValidator.IncorrectReason>?
    )
}