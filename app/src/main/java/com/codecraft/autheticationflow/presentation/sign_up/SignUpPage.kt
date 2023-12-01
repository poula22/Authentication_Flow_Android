package com.codecraft.autheticationflow.presentation.sign_up

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SignUpPage(modifier:Modifier){
    Box(modifier = modifier.fillMaxWidth()){
        Text(modifier = modifier.align(Alignment.Center), text = "Sign up page")
    }
}