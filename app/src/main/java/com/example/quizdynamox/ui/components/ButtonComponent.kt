package com.example.quizdynamox.ui.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonComponent(labelText: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(vertical = 16.dp),
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colorScheme.primary,
        ),
        shape = ShapeDefaults.Small
    ) {
        Text(text = labelText, color = MaterialTheme.colorScheme.surface)
    }
}