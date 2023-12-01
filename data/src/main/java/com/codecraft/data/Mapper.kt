package com.codecraft.data

import com.codecraft.data.api.model.LogInResponse
import com.codecraft.data.api.model.LogOutResponse
import com.codecraft.data.api.model.SignUpResponse
import com.codecraft.domain.model.user.UserLogInDomainModel
import com.codecraft.domain.model.user.UserLogOutDomainModel
import com.codecraft.domain.model.user.UserSignUpDomainModel
import com.codecraft.domain.model.user.utils.Email

fun LogInResponse.toUserLogIn() = UserLogInDomainModel(
    name = name,
    email = if (isVerified) Email.VerifiedEmail(email) else Email.UnVerifiedEmail.of(email)!!,
    token = token
)
fun SignUpResponse.toUserSignUp() =
    UserSignUpDomainModel(email = Email.UnVerifiedEmail.of(email)!!, message = message)

fun LogOutResponse.toUserLogOut() = UserLogOutDomainModel(message = message)