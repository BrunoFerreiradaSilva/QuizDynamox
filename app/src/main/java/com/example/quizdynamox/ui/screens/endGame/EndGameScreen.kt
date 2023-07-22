package com.example.quizdynamox.ui.screens.endGame

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.finish_game_score, state.value.playerScore),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 12.dp)
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
                    player.score
                )
            }
        }
    }


}