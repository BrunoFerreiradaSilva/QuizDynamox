package com.example.quizdynamox.ui.screens.endGame

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizdynamox.ui.components.ButtonComponent
import com.example.quizdynamox.ui.components.CardEndGameComponent

@Composable
fun EndGameScreen(
    onHomeScreen: () -> Unit?
) {
    val viewModel = hiltViewModel<EndGameViewModel>()
    val state = viewModel.uiState.collectAsState()

    BackHandler { }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        CardEndGameComponent(
            state.value.playerName,
            state.value.playerScore,
            MaterialTheme.colorScheme.tertiaryContainer
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {

            ButtonComponent(labelText = "Jogar Denovo") {
                onHomeScreen()
                viewModel.insertPlayerRoom()
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items(state.value.allPlayers) { player ->
                CardEndGameComponent(
                    player.name,
                    player.score,
                    MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }


}