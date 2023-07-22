package com.example.quizdynamox.ui.screens.quiz

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.quizdynamox.ui.components.ResponseQuestionComponent
import com.example.quizdynamox.ui.components.TextErrorComponent


@Composable
fun QuizScreen(onEndGameScreen: (Int) -> Unit) {
    val viewModel = hiltViewModel<QuizViewModel>()
    val state by viewModel.uiState.collectAsState()

    BackHandler { }

    if (state.finishTheGame) {
        onEndGameScreen(state.scoreGame)
    }

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    if (state.showError) {

    }


    if (state.showData) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Question(state.statement, state.currentQuestion)

            Options(state.options) { index: Int ->
                viewModel.selectOptions(index)
            }

            StateButtons(
                showSendButton = state.showSendButton,
                showNextButton = state.showNextButton,
                showResult = state.showResult,
                getNextQuestion = viewModel::getNextQuestion,
                showMessageError = state.showMessageError
            ) {
                viewModel.sendSelectedOptions(state.questionId)
            }
        }
    }


}

@Composable
private fun StateButtons(
    showSendButton: Boolean,
    showNextButton: Boolean,
    showResult: Boolean?,
    getNextQuestion: () -> Unit,
    showMessageError: Boolean,
    sendAnswer: () -> Unit,


    ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (showMessageError){
            TextErrorComponent(
                messageError = stringResource(id = R.string.not_selected_answer)
            )
        }

        if (showSendButton) {
            ButtonComponent(labelText = "Send answer") {
                sendAnswer()
            }
        }

        if (showNextButton) {
            showResult?.let { isAnswerCorrect ->
                ResponseQuestionComponent(isAnswerCorrect)
            }
            ButtonComponent(labelText = "Next answer") {
                getNextQuestion()
            }
        }
    }
}

@Composable
private fun Options(
    options: List<OptionUi>,
    selectOptions: (index: Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Resposta",
            modifier = Modifier.padding(vertical = 10.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        options.forEach { item ->
            Row(modifier = Modifier.padding(end = 2.dp, bottom = 4.dp)) {
                RadioButton(
                    selected = item.isSelected,
                    onClick = { selectOptions(item.index) },
                    enabled = item.isEnabled
                )
                Text(
                    text = item.text,
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
private fun Question(statement: String, currentQuestion: Float) {
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .defaultMinSize(minHeight = 150.dp)
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = "Pergunta",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,

            )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Text(
            text = statement,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        val currentQuestionInt = (currentQuestion * 10).toInt()
        Text(
            text = "${currentQuestionInt}/10",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Center
        )

        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            progress = currentQuestion,
            color = Color.White,
            trackColor = Color.Black
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))
    }
}