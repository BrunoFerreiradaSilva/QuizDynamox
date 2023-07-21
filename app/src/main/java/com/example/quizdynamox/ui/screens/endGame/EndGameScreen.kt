package com.example.quizdynamox.ui.screens.endGame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quizdynamox.navigation.Screens
import com.example.quizdynamox.ui.components.ButtonComponent

@Composable
fun EndGameScreen(
    navHostController: NavHostController?,
    isLoading: MutableState<Boolean>
) {
    val endGameViewModel = hiltViewModel<EndGameViewModel>()
    val endGameState = endGameViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            ButtonComponent(labelText = "Jogar Denovo", isLoading) {
                navHostController?.navigate(Screens.InitialScreen.route)
            }
        }

        LazyColumn() {
            items(endGameState.value.players) { item ->
                Card(modifier = Modifier.padding(8.dp)) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Nome: ${item.name}")
                        Text(text = "Pontuação: ${item.score}")
                    }
                }
            }
        }
    }
}