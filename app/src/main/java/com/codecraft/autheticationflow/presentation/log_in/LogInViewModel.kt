package com.codecraft.autheticationflow.presentation.log_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codecraft.domain.model.Resource
import com.codecraft.domain.model.user.UserLogInDomainModel
import com.codecraft.domain.model.user.utils.Validator
import com.codecraft.domain.custom_exception.AuthException
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
            if (isLogInValid(email, password)) logInUseCase(email, password).collectLatest {
                handleLogInResponse(it)
            }
        }
    }

    private suspend fun isLogInValid(email: String, password: String): Boolean {
        val isEmailValid = Validator.validateEmail(email, true)
        if (!isEmailValid) {
            _logInScreenState.emit(LogInUiState.Error("Email is not Valid"))
            return false
        } else {
            val isPasswordValid = Validator.validatePassword(password)
            if (!isPasswordValid) {
                _logInScreenState.emit(LogInUiState.Error("Password is not Valid"))
                return false
            }
        }
        return true
    }

    private suspend fun handleLogInResponse(logInResponse: Resource<UserLogInDomainModel>) {
        when (logInResponse) {
            is Resource.Success -> _logInScreenState.emit(LogInUiState.Success(logInResponse.data))
            is Resource.Error -> handleAuthExceptions(logInResponse.exception)
            is Resource.Loading -> _logInScreenState.emit(LogInUiState.Loading)
        }
    }

    private suspend fun handleAuthExceptions(it: Throwable) {
        when (it) {
            is AuthException -> {
                when (it) {
                    is AuthException.UserNotFound -> _logInScreenState.emit(
                        LogInUiState.Error(LoginSideEffectDialog.ShowGoToSignUpDialogPopUp(it.errorMessage))
                    )

                    is AuthException.UnverifiedEmail -> _logInScreenState.emit(
                        LogInUiState.Error(
                            LoginSideEffectDialog.ShowEmailVerificationEmailDialogPopUp(it.errorMessage)
                        )
                    )

                    is AuthException.UnknownError -> _logInScreenState.emit(
                        LogInUiState.Error(LoginSideEffectDialog.ShowUnknownErrorDialogPopUp(it.errorMessage))
                    )
                }
            }

            else -> _logInScreenState.emit(LogInUiState.Error(it.message ?: "Something went wrong"))
        }
    }
}