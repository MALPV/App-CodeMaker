package cl.inventionchile.codemaker.data.remote.response


import cl.inventionchile.codemaker.data.remote.dto.DtoRequest
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("request")
    val dtoRequest: DtoRequest,
    @SerializedName("code")
    val code: String
)