package com.codecraft.domain.use_case

import com.codecraft.domain.model.Resource
import com.codecraft.domain.model.asResult
import com.codecraft.domain.model.user.UserSignUpDomainModel
import com.codecraft.domain.repo.AuthenticationRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: AuthenticationRepository){
    fun invoke(name: String,password:String,email:String) = flow {
        emit(repository.signUp(name, email, password))
    }.asResult()
}