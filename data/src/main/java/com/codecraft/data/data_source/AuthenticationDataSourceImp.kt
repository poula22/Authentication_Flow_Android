package com.codecraft.data.data_source

import com.codecraft.data.api.model.LogInRequest
import com.codecraft.data.api.model.LogOutRequest
import com.codecraft.data.api.model.SignUpRequest
import com.codecraft.data.api.web_service.AuthenticationWebService
import com.codecraft.data.toUserLogIn
import com.codecraft.data.toUserLogOut
import com.codecraft.data.toUserSignUp
import com.codecraft.domain.data_source.AuthenticationDataSource
import com.codecraft.domain.model.user.UserLogInDomainModel
import com.codecraft.domain.model.user.UserLogOutDomainModel
import com.codecraft.domain.model.user.UserSignUpDomainModel
import com.codecraft.domain.retrofit.RetrofitManager
import javax.inject.Inject

class AuthenticationDataSourceImp @Inject constructor(
    retrofitManager: RetrofitManager
):AuthenticationDataSource {
    private val authenticationWebService=retrofitManager.buildWebService(AuthenticationWebService::class.java)

    override suspend fun logIn(email: String, password: String): UserLogInDomainModel =
        authenticationWebService.logIn(LogInRequest(email,password)).toUserLogIn()

    override suspend fun logOut(token: String): UserLogOutDomainModel =
        authenticationWebService.logOut(LogOutRequest(token)).toUserLogOut()

    override suspend fun signUp(name: String, email: String, password: String): UserSignUpDomainModel =
        authenticationWebService.signUp(SignUpRequest(email,password,name)).toUserSignUp()
}