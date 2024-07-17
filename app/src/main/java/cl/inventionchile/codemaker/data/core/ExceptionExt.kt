package cl.inventionchile.codemaker.data.core

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Exception.getMessageError() =
    when (this) {
        is SocketTimeoutException -> "Verifique la conexión a internet e intente nuevamente."
        is ConnectException,
        is UnknownHostException -> "Sin conexión a internet, verifique e intente nuevamente."
        else -> "Error inesperado, contacte al administrador."
    }