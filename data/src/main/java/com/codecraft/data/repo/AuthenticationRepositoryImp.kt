package com.codecraft.data.repo

import com.codecraft.domain.data_source.AuthenticationDataSource
import com.codecraft.domain.model.Resource
import com.codecraft.domain.model.user.UserLogInDomainModel
import com.codecraft.domain.model.user.UserLogOutDomainModel
import com.codecraft.domain.model.user.UserSignUpDomainModel
import com.codecraft.domain.custom_exception.AuthenticationException
import com.codecraft.domain.repo.AuthenticationRepository
import retrofit2.HttpException
import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor(
    private val dataSource: AuthenticationDataSource
) : AuthenticationRepository {
    override suspend fun logIn(email: String, password: String): Resource<UserLogInDomainModel> {
        try {
            return Resource.Success(
                dataSource.logIn(email = email, password = password)
            )
        } catch (ex: HttpException) {
            val authException = AuthenticationException.of(ex.code())
            if (authException == null) throw ex
            else throw authException
        }
    }

    override suspend fun logOut(token: String): Resource<UserLogOutDomainModel> {
        try {
            return Resource.Success(
                dataSource.logOut(token)
            )
        } catch (ex: HttpException) {
            val authException = AuthenticationException.of(ex.code())
            if (authException == null) throw ex
            else throw authException
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Resource<UserSignUpDomainModel> {
        try {
            return Resource.Success(
                dataSource.signUp(name = name, email = email, password = password)
            )
        } catch (ex: HttpException) {
            val authException = AuthenticationException.of(ex.code())
            if (authException == null) throw ex
            else throw authException
        }
    }
}