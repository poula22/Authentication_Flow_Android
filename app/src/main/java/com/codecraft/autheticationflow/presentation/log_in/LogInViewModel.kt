package com.codecraft.autheticationflow.presentation.log_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codecraft.domain.model.Resource
import com.codecraft.domain.model.user.UserLogInDomainModel
import com.codecraft.domain.model.user.utils.Validator
import com.codecraft.domain.custom_exception.AuthenticationException
import com.codecraft.domain.use_case.LogInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase
) : ViewModel() {
    private val _logInScreenState = MutableSharedFlow<LogInUiState>()
    val logInScreenState: SharedFlow<LogInUiState>
        get() = _logInScreenState

    fun logIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val isEmailValid = Validator.validateEmail(email, true)
            if (!isEmailValid) _logInScreenState.emit(LogInUiState.Error("Email is not Valid"))
            else {
                val isPasswordValid = Validator.validatePassword(password)
                if (!isPasswordValid) _logInScreenState.emit(LogInUiState.Error("Password is not Valid"))
                else {
                    logInUseCase.invoke(email, password).collectLatest {
                        handleLogInResponse(it)
                    }
                }
            }

        }
    }

    private suspend fun handleLogInResponse(logInRespones: Resource<UserLogInDomainModel>) {
        when(logInRespones){
            is Resource.Success-> _logInScreenState.emit(LogInUiState.Success(logInRespones.data))
            is Resource.Error -> handleAuthExceptions(logInRespones.exception)
            is Resource.Loading-> _logInScreenState.emit(LogInUiState.Loading)
        }
    }


    private suspend fun handleAuthExceptions(it: Throwable) {
        when (it) {
            is AuthenticationException -> {
                when (it.authException) {
                    AuthenticationException.AuthenticationExceptionType.USER_NOT_FOUND -> {
                        _logInScreenState.emit(
                            LogInUiState.Error(
                                LoginSideEffectDialog.ShowGoToSignUpDialogPopUp(
                                it.authException.errorMessage
                            ))
                        )
                    }

                    AuthenticationException.AuthenticationExceptionType.UNVERIFIED_EMAIL -> {
                        _logInScreenState.emit(
                            LogInUiState.Error(
                            LoginSideEffectDialog.ShowEmailVerificationEmailDialogPopUp(
                                it.authException.errorMessage
                            ))
                        )
                    }

                    AuthenticationException.AuthenticationExceptionType.UNKNOWN_ERROR -> {
                        _logInScreenState.emit(
                            LogInUiState.Error(
                            LoginSideEffectDialog.ShowUnknownErrorDialogPopUp(
                                it.authException.errorMessage
                            ))
                        )
                    }
                }
            }

            else -> _logInScreenState.emit(LogInUiState.Error(it.message ?: "Something went wrong"))
        }
    }
}