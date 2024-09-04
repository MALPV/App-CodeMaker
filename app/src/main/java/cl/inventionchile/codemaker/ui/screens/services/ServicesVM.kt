package cl.inventionchile.codemaker.ui.screens.services

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.inventionchile.codemaker.data.local.entity.EttServices
import cl.inventionchile.codemaker.data.usecases.AddServicesUseCase
import cl.inventionchile.codemaker.data.usecases.DeleteServicesUseCase
import cl.inventionchile.codemaker.data.usecases.ObserveServicesUseCase
import cl.inventionchile.codemaker.data.usecases.UpdateSelectedServiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServicesVM
@Inject
constructor(
    private val observeServicesUseCase: ObserveServicesUseCase,
    private val addServicesUseCase: AddServicesUseCase,
    private val deleteServicesUseCase: DeleteServicesUseCase,
    private val updateSelectedServiceUseCase: UpdateSelectedServiceUseCase
): ViewModel() {

    val services = mutableStateListOf<EttServices>()

    init {
        observeServices()
    }

    private fun observeServices() =  viewModelScope.launch {
        observeServicesUseCase
            .invoke()
            .collect { newServices ->
                services.clear()
                services.addAll(newServices)
            }
    }

    fun updateServicesByUser(services: EttServices) = viewModelScope.launch {
        updateSelectedServiceUseCase.invoke(services)
    }

    fun addServices(
        label: String,
        url: String,
        api: String
    ) = viewModelScope.launch { addServicesUseCase.invoke(label, url, api) }

    fun deleteServices(id: Long) = viewModelScope.launch { deleteServicesUseCase.invoke(id) }

}