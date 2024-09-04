package cl.inventionchile.codemaker.data.usecases

import android.util.Log
import cl.inventionchile.codemaker.data.core.ResultState
import cl.inventionchile.codemaker.data.core.getMessageError
import cl.inventionchile.codemaker.data.core.millisecondsToDate
import cl.inventionchile.codemaker.data.local.AppDatabase
import cl.inventionchile.codemaker.data.local.entity.EttUser
import cl.inventionchile.codemaker.data.remote.AppServices
import cl.inventionchile.codemaker.data.remote.request.LoginRequest
import com.auth0.android.jwt.JWT
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthenticateUseCase(
    private val appDatabase: AppDatabase,
    private val appServices: AppServices,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun invoke(
        username: String,
        password: String,
        remember: Boolean
    ) = flow {

        val serviceSelected = appDatabase.daoServices().getBySelected(selected = true)

        if (serviceSelected == null) {
            emit(ResultState.Error("Selecciona una API en la configuración"))
            return@flow
        }

        if (username.isEmpty()) {
            emit(ResultState.Error("Ingresa un usuario"))
            return@flow
        }

        if (password.isEmpty()) {
            emit(ResultState.Error("Ingresa la contraseña"))
            return@flow
        }

        emit(ResultState.Loading)

        try {

            val loginRequest = LoginRequest(username, password)
            val response = appServices.authenticate(
                url = serviceSelected.getUrlWebServices(),
                request = loginRequest
            )

            if (response.status == "ok"){

                val tokenDecode = JWT(response.dtoRequest.token)

                val created = tokenDecode.getClaim("iat").asString() ?: ""
                val expiration = tokenDecode.getClaim("exp").asString() ?: ""
                val code = tokenDecode.getClaim("code").asString() ?: ""

                val user = EttUser(
                    username = username,
                    password = password,
                    code = code,
                    created = created.toLong() * 1000,
                    expiration = expiration.toLong() * 1000,
                    remember = remember
                )

                appDatabase.daoUser().save(user)

                emit(ResultState.Success(true))

            }else{
                emit(ResultState.Error(response.dtoRequest.message))
            }

        }catch (e: Exception){
            Log.e("invoke", "error: $e")
            emit(ResultState.Error((e).getMessageError()))
        }


    }.flowOn(ioDispatcher)

}