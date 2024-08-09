package cl.inventionchile.codemaker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.inventionchile.codemaker.data.local.dao.DaoServices
import cl.inventionchile.codemaker.data.local.dao.DaoUser
import cl.inventionchile.codemaker.data.local.entity.EttServices
import cl.inventionchile.codemaker.data.local.entity.EttUser

@Database(
    entities = [
        EttUser::class,
        EttServices::class
    ],
    version = AppDatabase.VERSION,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        const val VERSION = 7
        const val NAME = "code-maker.db"
    }

    abstract fun daoUser(): DaoUser
    abstract fun daoServices(): DaoServices

}