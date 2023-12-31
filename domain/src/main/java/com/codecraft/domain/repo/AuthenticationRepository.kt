package com.codecraft.domain.repo

import com.codecraft.domain.model.Resource
import com.codecraft.domain.model.user.UserLogInDomainModel
import com.codecraft.domain.model.user.UserLogOutDomainModel
import com.codecraft.domain.model.user.UserSignUpDomainModel

interface AuthenticationRepository {
    suspend fun logIn(email:String,password:String):UserLogInDomainModel
    suspend fun logOut(token:String):UserLogOutDomainModel
    suspend fun signUp(name:String,email: String,password: String):UserSignUpDomainModel
}