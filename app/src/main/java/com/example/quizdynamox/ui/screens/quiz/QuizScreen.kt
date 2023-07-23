package com.example.quizdynamox.ui.screens.quiz

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
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
import com.example.quizdynamox.ui.screens.error.ErrorScreen
import com.example.quizdynamox.ui.screens.load.LoadingScreen


@Composable
fun QuizScreen(onEndGameScreen: (Int) -> Unit) {
    val viewModel = hiltViewModel<QuizViewModel>()
    val state by viewModel.uiState.collectAsState()

    BackHandler { }

    if (state.finishTheGame) {
        onEndGameScreen(state.score)
    }

    if (state.isLoading) {
        LoadingScreen()
    }

    if (state.showError) {
        ErrorScreen() {
            viewModel.retryGetQuestion()
        }
    }

    if (state.showData) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Question(state.statement, state.progressCount, state.currentProgress)

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
        verticalArrangement = Arrangement.Center,
    ) {

        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        if (showMessageError) {
            Text(
                text = stringResource(id = R.string.not_selected_answer),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        if (showSendButton) {
            ButtonComponent(labelText = stringResource(id = R.string.send_answer)) {
                sendAnswer()
            }
        }

        if (showNextButton) {
            showResult?.let { isAnswerCorrect ->
                ResponseQuestionComponent(isAnswerCorrect)
            }
            ButtonComponent(labelText = stringResource(id = R.string.next_question)) {
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
    Spacer(modifier = Modifier.padding(top = 12.dp))
    Column(
        modifier = Modifier
            .defaultMinSize(minHeight = 380.dp, minWidth = 380.dp)
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.answer),
            modifier = Modifier.padding(vertical = 10.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )

        options.forEach { item ->
            Row(modifier = Modifier.padding(end = 2.dp, bottom = 4.dp)) {
                Spacer(modifier = Modifier.padding(bottom = 12.dp))
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    onClick = { selectOptions(item.index) },
                    enabled = item.isEnabled,
                    colors = ButtonDefaults.buttonColors(if (item.isSelected) MaterialTheme.colorScheme.primary else Color.White),
                    shape = ShapeDefaults.Small
                ) {
                    Text(
                        text = item.text,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(8.dp),
                        color = if (item.isSelected) Color.White else MaterialTheme.colorScheme.primary
                    )
                }
            }

        }
    }
}

@Composable
private fun Question(statement: String, progressCount: Int, progress:Float) {
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .defaultMinSize(minHeight = 150.dp)
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.question),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.surface
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Text(
            text = statement,
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.surface
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Text(
            text = stringResource(id = R.string.progress, progressCount,MAX_QUESTIONS),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.surface
        )

        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            progress = progress,
            color = Color.White,
            trackColor = Color.Black
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))
    }
}