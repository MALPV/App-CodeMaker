package cl.inventionchile.codemaker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EttUser(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val username: String,
    val password: String,
    val code: String,
    val created: Long,
    val expiration: Long,
    val remember: Boolean
)
