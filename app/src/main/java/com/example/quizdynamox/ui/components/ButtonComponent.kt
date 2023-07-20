package com.example.quizdynamox.ui.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonComponent(labelText: String, isLoading: MutableState<Boolean>, onClick: () -> Unit) {
    if (isLoading.value){
        CircularProgressIndicator(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }else{
        Button(
            modifier = Modifier
                .padding(vertical = 16.dp),
            onClick = {
                isLoading.value = true
                onClick()
                      },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.surface
            ),
            shape = ShapeDefaults.Small
        ) {
            Text(text = labelText, color = MaterialTheme.colorScheme.surface)
        }
    }



}