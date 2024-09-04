package cl.inventionchile.codemaker.data.usecases

import cl.inventionchile.codemaker.data.local.AppDatabase
import cl.inventionchile.codemaker.data.local.entity.EttServices
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AddServicesUseCase(
    private val appDatabase: AppDatabase,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun invoke(
        label: String,
        url: String,
        api: String
    ) = withContext(ioDispatcher){
        val newServices = EttServices(
            label = label,
            url = url,
            api = api,
            selected = false
        )
        appDatabase.daoServices().save(newServices)
    }

}