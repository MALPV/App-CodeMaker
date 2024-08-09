package cl.inventionchile.codemaker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EttServices(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val label: String,
    val url: String
)
