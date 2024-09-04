package cl.inventionchile.codemaker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EttServices(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val label: String,
    val url: String,
    val api: String,
    val endPoint: String = "authenticate",
    val selected: Boolean
){
    fun getUrlWebServices() = "https://$url$api$endPoint"
}
