package cl.inventionchile.codemaker.data.usecases

import cl.inventionchile.codemaker.data.local.AppDatabase
import cl.inventionchile.codemaker.data.local.entity.EttUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetUserUseCase(
    private val appDatabase: AppDatabase,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun get() = withContext(ioDispatcher){
        appDatabase.daoUser().get()
    }

    suspend fun observe() = withContext(ioDispatcher){
        appDatabase.daoUser().observe()
    }


}