package cl.inventionchile.codemaker.ui.screens.home

import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.inventionchile.codemaker.data.core.ResultState
import cl.inventionchile.codemaker.data.local.entity.EttUser
import cl.inventionchile.codemaker.data.usecases.AuthenticateUseCase
import cl.inventionchile.codemaker.data.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class HomeVM
@Inject
constructor(
    private val authenticateUseCase: AuthenticateUseCase,
    private val getUserUseCase: GetUserUseCase
): ViewModel() {

    private val user = mutableStateOf<EttUser?>(null)

    val code = mutableStateOf("")
    val progress = mutableFloatStateOf(0f)
    private val expiration = mutableLongStateOf(0L)
    private val created = mutableLongStateOf(0L)

    val errorMsg = mutableStateOf("")

    private val vmJob = Job()

    init {
        runProcess()
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        getUserUseCase.get()?.let { userObserve ->
            user.value = userObserve
            code.value = userObserve.code
            Log.i("updateExpirationProgress", "userObserve.created ${userObserve.created}")
            Log.i("updateExpirationProgress", "userObserve.expiration ${userObserve.expiration}")
            created.longValue = userObserve.created
            expiration.longValue = userObserve.expiration
            progress.floatValue = 1f
        }

    }

    fun refreshCode() = authenticateUseCase.invoke(
        username = user.value?.username ?: "",
        password = user.value?.password ?: "",
        remember = user.value?.remember ?: false,
    ).onEach { resultState ->

        when(resultState){
            is ResultState.Success -> { getUser() }
            is ResultState.Loading -> { code.value = "" }
            is ResultState.Error -> { errorMsg.value = resultState.message }
        }

    }.launchIn(viewModelScope)

    private fun runProcess() = viewModelScope.launch {
        while (vmJob.isActive){
            delay(1.seconds)
            updateExpirationProgress()
        }
    }

    private fun updateExpirationProgress() {
        user.value?.let { userData ->
            val currentTime = System.currentTimeMillis()
            val timeRemaining = expiration.longValue - currentTime
            val totalDuration = userData.expiration - userData.created

            Log.i("updateExpirationProgress", "currentTimeMillis $currentTime")
            Log.i("updateExpirationProgress", "totalDuration $totalDuration")
            Log.i("updateExpirationProgress", "timeRemaining $timeRemaining")

            if (timeRemaining <= 0) {
                Log.i("updateExpirationProgress", "UPDATEE $timeRemaining")
                refreshCode()
            }

            val tmpProgress = timeRemaining.toDouble() / totalDuration
            progress.floatValue = tmpProgress.toFloat()
            Log.i("updateExpirationProgress", "progress.floatValue ${progress.floatValue}")
        }
    }

}