package com.example.quizdynamox.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizdynamox.R

@Composable
fun CardEndGameComponent(playerName: String, playerScore: Int) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onTertiaryContainer),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.name_player_in_cache, playerName),
                color = MaterialTheme.colorScheme.surface,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.score_player_in_cache, playerScore),
                color = MaterialTheme.colorScheme.surface,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun PreviewCardEndGameComponent() {
    CardEndGameComponent(playerName = "Exemplo", playerScore = 0)
}