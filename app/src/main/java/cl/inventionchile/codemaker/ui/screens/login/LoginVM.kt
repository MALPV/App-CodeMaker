package cl.inventionchile.codemaker.ui.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.inventionchile.codemaker.data.core.ResultState
import cl.inventionchile.codemaker.data.usecases.AuthenticateUseCase
import cl.inventionchile.codemaker.data.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM
@Inject
constructor(
    private val authenticateUseCase: AuthenticateUseCase,
    private val getUserUseCase: GetUserUseCase
): ViewModel() {

    val username = mutableStateOf("test@test.cl")
    val password = mutableStateOf("v2c47mk7jd#BB")
    val remember = mutableStateOf(false)

    val isLoading = mutableStateOf(false)
    val errorMsg = mutableStateOf("")

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        getUserUseCase.get()?.let { user ->
            username.value = user.username
            password.value = user.password
            remember.value = user.remember
        }
    }

    fun authenticate(
        onSuccess: () -> Unit
    ) = authenticateUseCase.invoke(
        username = username.value,
        password = password.value,
        remember = remember.value
    ).onEach { resultState ->

        when(resultState){
            is ResultState.Success -> {
                isLoading.value = false
                onSuccess()
            }
            is ResultState.Loading -> { isLoading.value = true }
            is ResultState.Error -> {
                isLoading.value = false
                errorMsg.value = resultState.message
            }
        }

    }.launchIn(viewModelScope)

}