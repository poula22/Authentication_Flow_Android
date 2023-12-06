package com.codecraft.domain.custom_exception

sealed class AuthException private constructor(val errorCode: Int, val errorMessage: String):Exception() {
    companion object {
        fun of(errorCode: Int, errorMessage: String?): AuthException? = when (errorCode) {
            404 -> UserNotFound(errorMessage)
            401 -> UnverifiedEmail(errorMessage)
            520 -> UnknownError(errorMessage)
            else -> null
        }
    }
    class UserNotFound(errorMessage: String?) : AuthException(
            errorCode = 404,
            errorMessage = errorMessage ?: "User not found. Please sign up first"
        )
    class UnverifiedEmail(errorMessage: String?) : AuthException(
            errorCode = 401,
            errorMessage = errorMessage ?: "Please verify email first"
        )
    class UnknownError(errorMessage: String?) : AuthException(
        errorCode = 520,
        errorMessage = errorMessage ?: "Something went wrong"
    )
}