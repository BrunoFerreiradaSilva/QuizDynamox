package com.example.quizdynamox.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quizdynamox.R

@Composable
fun TextErrorComponent(messageError: String) {
    Text(
        text = messageError,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(top = 8.dp)
    )
}