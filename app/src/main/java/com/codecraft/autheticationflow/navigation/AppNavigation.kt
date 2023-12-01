package com.codecraft.autheticationflow.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codecraft.autheticationflow.presentation.log_in.LogInPage
import com.codecraft.autheticationflow.presentation.sign_up.SignUpPage
import com.codecraft.autheticationflow.presentation.verification_email.EmailVerificationPage

@Composable
fun AppNavigation() {
    val navController = rememberNavController() //create nav controller by rememberNavController
    NavHost(
        navController = navController,
        startDestination = "login",
    ) {

        composable(route="login"){
            LogInPage(modifier = Modifier)
        }
        
        composable(route="sign up"){
            SignUpPage(modifier = Modifier)
        }
        
        composable(route = "email_verification"){
            EmailVerificationPage(modifier = Modifier)
        }

    }
}