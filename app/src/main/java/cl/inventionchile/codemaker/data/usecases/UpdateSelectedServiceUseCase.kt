package cl.inventionchile.codemaker.data.usecases

import cl.inventionchile.codemaker.data.local.AppDatabase
import cl.inventionchile.codemaker.data.local.entity.EttServices
import cl.inventionchile.codemaker.data.local.entity.EttUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UpdateSelectedServiceUseCase(
    private val appDatabase: AppDatabase,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun invoke(service: EttServices) = withContext(ioDispatcher){
        val currentSelected = appDatabase.daoServices().getBySelected(selected = true)
        currentSelected?.let { appDatabase.daoServices().update(it.copy(selected = false)) }

        appDatabase.daoServices().update(service.copy(selected = true))
    }

}