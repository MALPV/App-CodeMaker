package cl.inventionchile.codemaker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.inventionchile.codemaker.data.local.entity.EttServices
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoServices {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(service: EttServices)

    @Update
    suspend fun update(service: EttServices)

    @Query("SELECT * FROM EttServices WHERE id = :id")
    suspend fun get(id: Long): EttServices?

    @Query("SELECT * FROM EttServices WHERE selected = :selected")
    suspend fun getBySelected(selected: Boolean): EttServices?

    @Query("SELECT * FROM EttServices")
    fun observeAll(): Flow<List<EttServices>>

    @Query("DELETE FROM EttServices WHERE id = :id")
    suspend fun delete(id: Long)

}