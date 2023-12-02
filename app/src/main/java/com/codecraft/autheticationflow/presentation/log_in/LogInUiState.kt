package com.codecraft.autheticationflow.presentation.log_in

import com.codecraft.domain.model.user.UserLogInDomainModel

sealed interface LogInUiState{
    data class Success(val userLogin:UserLogInDomainModel):LogInUiState
    data class Error<T>(val data:T):LogInUiState
    object Loading:LogInUiState
}