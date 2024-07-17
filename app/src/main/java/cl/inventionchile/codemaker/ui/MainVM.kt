package cl.inventionchile.codemaker.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.inventionchile.codemaker.data.local.entity.EttUser
import cl.inventionchile.codemaker.data.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM
@Inject
constructor(
    private val getUserUseCase: GetUserUseCase
): ViewModel() {

    val user = mutableStateOf<EttUser?>(null)
    val isLoading = mutableStateOf(true)

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        user.value = getUserUseCase.get()
        delay(1000)
        isLoading.value = false
    }

}