package cl.inventionchile.codemaker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import cl.inventionchile.codemaker.data.local.entity.EttUser
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoUser {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(user: EttUser)

    @Query("SELECT * FROM EttUser WHERE id = 0")
    fun observe(): Flow<EttUser?>

    @Query("SELECT * FROM EttUser WHERE id = 0")
    suspend fun get(): EttUser?

}