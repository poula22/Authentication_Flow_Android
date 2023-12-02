package com.codecraft.autheticationflow.presentation.log_in

import androidx.compose.runtime.Composable
import com.codecraft.autheticationflow.presentation.comon_component.ShowLogInAlertDialog

sealed class LoginSideEffectDialog(val message:String){
    @Composable
    abstract fun DoAction()

    class ShowGoToSignUpDialogPopUp(message: String):LoginSideEffectDialog(message){
        @Composable
        override fun DoAction() {
            ShowLogInAlertDialog(
                value = message,
                confirmButtonClick = {
                    // TODO: Navigate to sign up
                },
                confirmButtonText = "sign up",
                hasDismissButton = true,
                dismissButtonText = "cancel"
            ) {}
        }
    }

    class ShowEmailVerificationEmailDialogPopUp(message: String):LoginSideEffectDialog(message){
        @Composable
        override fun DoAction() {
            ShowLogInAlertDialog(
                value = message,
                confirmButtonClick = {
                    // TODO: Navigate to verification
                },
                confirmButtonText = "verify now",
                hasDismissButton = true,
                dismissButtonText = "cancel"
            ) {}
        }
    }

    class ShowUnknownErrorDialogPopUp(message: String):LoginSideEffectDialog(message){
        @Composable
        override fun DoAction() {
            ShowLogInAlertDialog(
                value = message,
                confirmButtonText = "Ok"
            )
        }
    }
}
