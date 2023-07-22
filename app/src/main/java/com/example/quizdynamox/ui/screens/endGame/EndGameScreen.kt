package com.example.quizdynamox.ui.screens.endGame

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizdynamox.R
import com.example.quizdynamox.ui.components.ButtonComponent
import com.example.quizdynamox.ui.components.CardEndGameComponent

@Composable
fun EndGameScreen(
    onHomeScreen: () -> Unit?
) {
    val viewModel = hiltViewModel<EndGameViewModel>()
    val state = viewModel.uiState.collectAsState()

    BackHandler { }


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                text = stringResource(id = R.string.finish_game_name, state.value.playerName),
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }

        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.finish_game_score, state.value.playerScore),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

        item {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                ButtonComponent(labelText = stringResource(id = R.string.player_again)) {
                    onHomeScreen()
                    viewModel.insertPlayerRoom()
                }
            }
        }

        items(state.value.allPlayerEntities) { player ->
            CardEndGameComponent(
                player.name,
                player.score
            )
        }
    }
}