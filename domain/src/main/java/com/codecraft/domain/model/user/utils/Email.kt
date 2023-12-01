package com.codecraft.domain.model.user.utils

sealed class Email {
    data class VerifiedEmail(val email: String): Email()
    class UnVerifiedEmail private constructor(val email: String): Email(){
        companion object{
            fun of(email: String): UnVerifiedEmail?{
                if (Validator.validateEmail(email,false)){
                    return UnVerifiedEmail(email)
                }
                return null
            }
        }
    }
}