package com.codecraft.data.api.web_service

import com.codecraft.data.api.model.LogInRequest
import com.codecraft.data.api.model.LogInResponse
import com.codecraft.data.api.model.LogOutRequest
import com.codecraft.data.api.model.LogOutResponse
import com.codecraft.data.api.model.SignUpRequest
import com.codecraft.data.api.model.SignUpResponse
import retrofit2.http.POST

interface AuthenticationWebService {
    @POST("auth/login")
    suspend fun logIn(logInRequest: LogInRequest): LogInResponse

    @POST("auth/signup")
    suspend fun signUp(signUp: SignUpRequest): SignUpResponse

    @POST("auth/logout")
    suspend fun logOut(logOut: LogOutRequest): LogOutResponse
}