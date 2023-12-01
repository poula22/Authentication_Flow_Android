package com.codecraft.domain.data_source

import com.codecraft.domain.model.user.UserLogInDomainModel
import com.codecraft.domain.model.user.UserLogOutDomainModel
import com.codecraft.domain.model.user.UserSignUpDomainModel

interface AuthenticationDataSource {
    //Domain Model
    suspend fun logIn(email:String,password:String): UserLogInDomainModel
    suspend fun logOut(token:String): UserLogOutDomainModel
    suspend fun signUp(name:String,email: String,password: String): UserSignUpDomainModel
}