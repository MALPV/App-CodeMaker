package cl.inventionchile.codemaker.data.remote

import cl.inventionchile.codemaker.data.remote.request.LoginRequest
import cl.inventionchile.codemaker.data.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface AppServices {

    @POST
    suspend fun authenticate(
        @Url url: String,
        @Body request: LoginRequest
    ): LoginResponse

}