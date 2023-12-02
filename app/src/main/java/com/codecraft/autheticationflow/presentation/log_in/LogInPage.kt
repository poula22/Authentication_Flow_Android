package com.codecraft.autheticationflow.presentation.log_in

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codecraft.autheticationflow.presentation.comon_component.ShowLogInAlertDialog
import com.codecraft.autheticationflow.ui.theme.AutheticationFlowTheme
import com.codecraft.data.api.RetrofitManagerImp
import com.codecraft.data.data_source.AuthenticationDataSourceImp
import com.codecraft.data.repo.AuthenticationRepositoryImp
import com.codecraft.domain.use_case.LogInUseCase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInPage(modifier: Modifier, viewModel: LogInViewModel = hiltViewModel()) {
    val email = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "My App",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = modifier.height(16.dp))

        OutlinedTextField(
            value = email.value,
            label = { Text(text = "Email") },
            onValueChange = {
                email.value = it
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = "Password") },
            modifier = modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.logIn(email.value, password.value)
        }) {
            Text(text = "Log in")
        }
    }

    val screenState = viewModel.logInScreenState.collectAsState(initial = null)
    when (screenState.value) {
        is LogInUiState.Success -> {
            // TODO:  navigate to home
        }

        is LogInUiState.Error<*> -> {
            val errorState=(screenState.value as LogInUiState.Error<*>)
            when (errorState.data){
                is String->{
                    ShowLogInAlertDialog(
                        value = errorState.data,
                        confirmButtonText = "OK"
                    )
                }
                is LoginSideEffectDialog->{
                    errorState.data.DoAction()
                }
            }
        }

        is LogInUiState.Loading -> {
           Box(modifier = modifier.fillMaxWidth()){
               CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
           }
        }
        else -> {}
    }
}

@Preview(showBackground = true)
@Composable
fun LogInPreview() {
    AutheticationFlowTheme {
        LogInPage(
            modifier = Modifier,
            viewModel = LogInViewModel(
                LogInUseCase(
                    AuthenticationRepositoryImp(AuthenticationDataSourceImp(RetrofitManagerImp()))
                )
            )
        )
    }
}