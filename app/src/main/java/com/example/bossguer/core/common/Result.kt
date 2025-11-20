package com.example.bossguer.core.common

import java.lang.Exception

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    // El cambio clave: Hacemos que Error sea también genérico.
    // Esto elimina la necesidad de que el compilador infiera la relación
    // con 'Nothing', que es lo que está fallando.
    data class Error<T>(val exception: Exception) : Result<T>()
}
