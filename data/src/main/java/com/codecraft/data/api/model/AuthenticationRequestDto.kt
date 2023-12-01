package com.codecraft.data.api.model

sealed class AuthenticationRequestDto(val email:String, val password:String)

class LogInRequest(email: String,password: String):AuthenticationRequestDto(email,password)
class SignUpRequest(email: String,password: String,val name:String):AuthenticationRequestDto(email,password)
data class LogOutRequest(val token:String)