package com.example.quizdynamox.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ResponseQuestionComponent(message: String, color: Color, textColor: Color) {
    Box(modifier = Modifier.clip(ShapeDefaults.Medium)) {
        Text(
            text = message,
            modifier = Modifier
                .background(color = color)
                .padding(12.dp),
            color = textColor,
            fontWeight = FontWeight.W500
        )
    }

}