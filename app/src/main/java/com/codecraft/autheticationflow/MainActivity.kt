package com.codecraft.autheticationflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codecraft.MyApplication
import com.codecraft.autheticationflow.navigation.AppNavigation
import com.codecraft.autheticationflow.presentation.comon_component.showAlertDialog
import com.codecraft.autheticationflow.ui.theme.AutheticationFlowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutheticationFlowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val networkState=(application as MyApplication).networkHandler.networkState.collectAsState()
                    when(networkState.value){
                        true->{AppNavigation()}
                        false->{
                            showAlertDialog(value = "Network Error", confirmButtonText = "retry")
                        }
                    }
                }
            }
        }
    }
}