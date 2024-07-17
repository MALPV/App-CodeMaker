package cl.inventionchile.codemaker.data.remote.dto


import com.google.gson.annotations.SerializedName

data class DtoRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String
)