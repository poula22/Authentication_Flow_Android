package com.codecraft.domain.use_case

import com.codecraft.domain.model.Resource
import com.codecraft.domain.model.asResult
import com.codecraft.domain.model.user.UserLogOutDomainModel
import com.codecraft.domain.repo.AuthenticationRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val repository: AuthenticationRepository) {
    fun invoke(token: String) = flow { emit(repository.logOut(token)) }.asResult()
}