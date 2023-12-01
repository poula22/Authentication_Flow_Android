package com.codecraft.domain.custom_exception


class AuthenticationException private constructor(val authException: AuthenticationExceptionType):Exception() {
    companion object {
        fun of(errorCode: Int): AuthenticationException?{
            val type= typeProvider(errorCode) ?: return null
            return AuthenticationException(type)
        }
        //errorMessage
        private fun typeProvider(errorCode: Int): AuthenticationExceptionType? =
            when (errorCode) {
                AuthenticationExceptionType.USER_NOT_FOUND.errorCode -> AuthenticationExceptionType.USER_NOT_FOUND
                AuthenticationExceptionType.UNVERIFIED_EMAIL.errorCode -> AuthenticationExceptionType.UNVERIFIED_EMAIL
                AuthenticationExceptionType.UNKNOWN_ERROR.errorCode-> AuthenticationExceptionType.UNKNOWN_ERROR
                else-> null
            }
    }
    // error message comes from backend
    enum class AuthenticationExceptionType(val errorCode:Int, val errorMessage:String){
        USER_NOT_FOUND(errorCode = 404, errorMessage = "User not found. Please sign up first"),
        UNVERIFIED_EMAIL(errorCode = 401,errorMessage = "Please verify email first"),
        UNKNOWN_ERROR(errorCode = 520, errorMessage = "Something went wrong")
    }
}

