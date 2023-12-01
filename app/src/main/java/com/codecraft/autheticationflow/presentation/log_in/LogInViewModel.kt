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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase
) : ViewModel() {
    private val _logInScreenState = MutableSharedFlow<Resource<UserLogInDomainModel>>()
    val logInScreenState: SharedFlow<Resource<UserLogInDomainModel>>
        get() = _logInScreenState

    private val _logInScreenSideEffect = MutableSharedFlow<LoginSideEffectDialog>()
    val logInScreenSideEffect: SharedFlow<LoginSideEffectDialog>
        get() = _logInScreenSideEffect

    fun logIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val isEmailValid = Validator.validateEmail(email, true)
            if (!isEmailValid) _logInScreenState.emit(Resource.Error("Email is not Valid"))
            else {
                val isPasswordValid = Validator.validatePassword(password)
                if (!isPasswordValid) _logInScreenState.emit(Resource.Error("Password is not Valid"))
                else {
                    logInUseCase.invoke(email, password)
                        .catch {
                            handleAuthExceptions(it)
                        }.collectLatest {
                            _logInScreenState.emit(it)
                        }
                }
            }

        }
    }

    private suspend fun handleAuthExceptions(it: Throwable) {
        when (it) {
            is AuthenticationException -> {
                when (it.authException) {
                    AuthenticationException.AuthenticationExceptionType.USER_NOT_FOUND -> {
                        _logInScreenSideEffect.emit(
                            LoginSideEffectDialog.ShowGoToSignUpDialogPopUp(
                                it.authException.errorMessage
                            )
                        )
                    }

                    AuthenticationException.AuthenticationExceptionType.UNVERIFIED_EMAIL -> {
                        _logInScreenSideEffect.emit(
                            LoginSideEffectDialog.ShowEmailVerificationEmailDialogPopUp(
                                it.authException.errorMessage
                            )
                        )
                    }

                    AuthenticationException.AuthenticationExceptionType.UNKNOWN_ERROR -> {
                        _logInScreenSideEffect.emit(
                            LoginSideEffectDialog.ShowUnknownErrorDialogPopUp(
                                it.authException.errorMessage
                            )
                        )
                    }
                }
            }

            else -> _logInScreenState.emit(Resource.Error(it.message ?: "Something went wrong"))
        }
    }
}