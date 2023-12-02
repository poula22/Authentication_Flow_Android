package com.codecraft.autheticationflow.presentation.comon_component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ShowLogInAlertDialog(
    value: String,
    confirmButtonClick: () -> Unit = {},
    confirmButtonText: String,
    hasDismissButton: Boolean = false,
    dismissButtonText:String="",
    dismissButtonClick: () -> Unit = {},
) {
    if (hasDismissButton) {
        AlertDialog(
            onDismissRequest = {return@AlertDialog},
            confirmButton = {
                Button(onClick = confirmButtonClick) {
                    Text(text = confirmButtonText)
                }
            },
            text = { Text(text = value) },
            dismissButton ={
                Button(onClick = dismissButtonClick) {
                    Text(text = dismissButtonText)
                }
            }
        )
    } else {
        AlertDialog(
            onDismissRequest = {
                return@AlertDialog
            },
            confirmButton = {
                Button(onClick = confirmButtonClick) {
                    Text(text = confirmButtonText)
                }
            },
            text = { Text(text = value) }
        )
    }

}