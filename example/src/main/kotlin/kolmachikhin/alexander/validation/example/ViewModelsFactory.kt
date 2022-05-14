package kolmachikhin.alexander.validation.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

val viewModelsFactory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = EmailValidationViewModel(EmailValidator()) as T
}