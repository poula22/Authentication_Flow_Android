package com.codecraft.domain.model.user

import com.codecraft.domain.model.user.utils.Email

//val email: Email.UnVerifiedEmail,val password:String
data class UserSignUpDomainModel(val email: Email.UnVerifiedEmail, val message:String)