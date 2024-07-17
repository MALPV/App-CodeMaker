package cl.inventionchile.codemaker.data.core

sealed class  ResultState<out R>{
    data class Success<out T>(val data: T): ResultState<T>()
    data class Error(val message: String): ResultState<Nothing>()
    data object Loading: ResultState<Nothing>()
}