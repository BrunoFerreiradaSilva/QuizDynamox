package com.example.quizdynamox.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quizdynamox.R

@Composable
fun ResponseQuestionComponent(isAnswerCorrect: Boolean) {
    val message = if (isAnswerCorrect) {
        stringResource(id = R.string.result_success)
    } else {
        stringResource(id = R.string.result_error)
    }
    val color = if (isAnswerCorrect) {
        Color(0xFF6EB470)
    } else {
        Color.Black
    }
    val textColor = if (isAnswerCorrect) {
        Color.Black
    } else {
        Color.White
    }
    val icon = if (isAnswerCorrect) {
        Icons.Default.Check
    } else {
        Icons.Default.Close
    }
    val colorIcon = if (isAnswerCorrect){
        Color.Green
    }else{
        Color.Red
    }

    Box(
        modifier = Modifier
            .clip(ShapeDefaults.Medium)
            .background(color = color)
            .padding(12.dp)
    ) {
        Row {
            Text(
                text = message,
                color = textColor,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(end = 4.dp)
            )
            Icon(imageVector = icon, contentDescription = "icon response", tint = colorIcon)
        }

    }

}