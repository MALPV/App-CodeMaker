package cl.inventionchile.codemaker.data.usecases

import cl.inventionchile.codemaker.data.local.AppDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DeleteServicesUseCase(
    private val appDatabase: AppDatabase,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun invoke(
        id: Long
    ) = withContext(ioDispatcher){
        appDatabase.daoServices().delete(id)
    }

}
