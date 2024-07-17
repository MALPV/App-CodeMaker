package cl.inventionchile.codemaker.data.remote

import cl.inventionchile.codemaker.BuildConfig
import cl.inventionchile.codemaker.data.remote.request.LoginRequest
import cl.inventionchile.codemaker.data.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AppServices {

    companion object {

        private const val WS = "/api/inv/"
        const val URL_BASE = BuildConfig.API_URL + WS

    }

    @POST("authenticate")
    suspend fun authenticate(@Body request: LoginRequest): LoginResponse

}