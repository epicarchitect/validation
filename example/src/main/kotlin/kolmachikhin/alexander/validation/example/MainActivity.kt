package kolmachikhin.alexander.validation.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kolmachikhin.alexander.validation.Correct
import kolmachikhin.alexander.validation.Incorrect

class MainActivity : ComponentActivity() {

    private val emailValidationViewModel: EmailValidationViewModel by viewModels { viewModelsFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    val viewModelState by emailValidationViewModel.state.collectAsState()
                    viewModelState?.let { state ->
                        EmailValidationScreen(
                            state = state,
                            onEmailChanged = {
                                emailValidationViewModel.updateEmail(it)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmailValidationScreen(
    state: EmailValidationViewModel.State,
    onEmailChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            modifier = Modifier.padding(16.dp),
            value = state.email,
            onValueChange = onEmailChanged
        )

        when (val validatedEmail = state.validatedEmail) {
            is Correct -> {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Email valid"
                )
            }
            is Incorrect -> {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = when (validatedEmail.reason) {
                        is EmailValidator.IncorrectReason.Empty -> "Empty"
                        is EmailValidator.IncorrectReason.Pattern -> "Invalid format"
                    }
                )
            }
            null -> {
                /* no-op */
            }
        }
    }
}