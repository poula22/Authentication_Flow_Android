package com.codecraft.domain.model.user.utils

object Validator {
    fun validateEmail(email:String,regexCheck:Boolean):Boolean{
        if (email.isNotBlank() && email.isNotEmpty()){
            if (regexCheck){
                val pattern="[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
                if (email.matches(Regex(pattern)))
                    return true
            }
        }
        return false
    }

    fun validatePassword(password:String):Boolean{
        return password.length>6 && password.matches(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"))
    }
}