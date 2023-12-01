package com.codecraft.autheticationflow.presentation.verification_email

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun EmailVerificationPage(modifier:Modifier){
    Box(modifier = modifier.fillMaxWidth()){
        Text(modifier = modifier.align(Alignment.Center), text = "Email verification page")
    }
}