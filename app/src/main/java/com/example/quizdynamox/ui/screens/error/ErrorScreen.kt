package com.example.quizdynamox.ui.screens.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizdynamox.ui.components.ButtonComponent

@Composable
fun ErrorScreen(error: Boolean, tryAgain: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = "error",
            modifier = Modifier.size(100.dp),
            tint = Color.Red
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Não foi possivel carregar as perguntas",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        ButtonComponent(labelText = "Retry") {
            if (error) {
                tryAgain()
            }
        }
    }

}