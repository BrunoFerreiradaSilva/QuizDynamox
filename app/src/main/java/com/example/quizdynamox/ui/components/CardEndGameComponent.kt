package com.example.quizdynamox.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CardEndGameComponent(playerName:String, playerScore:Int) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Nome: $playerName",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Pontuação: $playerScore",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}