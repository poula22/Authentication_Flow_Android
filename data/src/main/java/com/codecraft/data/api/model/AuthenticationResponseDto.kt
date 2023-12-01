package com.codecraft.data.api.model

import com.codecraft.domain.model.user.utils.Email

sealed class AuthenticationResponseDto(val email:String)

class LogInResponse(email: String,val name:String,val token:String,val isVerified:Boolean):AuthenticationResponseDto(email)
class SignUpResponse(email: String,val message:String):AuthenticationResponseDto(email)
data class LogOutResponse(val message: String)
