package cl.inventionchile.codemaker.data.usecases

import cl.inventionchile.codemaker.data.local.AppDatabase
import cl.inventionchile.codemaker.data.local.entity.EttUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetUserUseCase(
    private val appDatabase: AppDatabase,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun get(): EttUser? = withContext(ioDispatcher){
        appDatabase.daoUser().get()
    }

    fun observe() = appDatabase.daoUser().observe()

}