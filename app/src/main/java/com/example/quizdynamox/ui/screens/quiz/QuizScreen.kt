package com.example.quizdynamox.ui.screens.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quizdynamox.model.entity.Answer
import com.example.quizdynamox.model.entity.QuestionEntity
import com.example.quizdynamox.navigation.Screens
import com.example.quizdynamox.ui.components.ButtonComponent
import com.example.quizdynamox.ui.components.ResponseQuestionComponent
import com.example.quizdynamox.ui.screens.endGame.EndGameScreen

@Composable
fun QuizScreen(navHostController: NavHostController) {
    val quizViewModel = hiltViewModel<QuizViewModel>()
    val questionState by quizViewModel.uiState.collectAsState()

    val selectedValue = remember { mutableStateOf("") }
    val count = remember { mutableStateOf(1) }
    val isLoading = remember { mutableStateOf(false) }
    val enableRadio = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        questionState.quiz?.let {question ->
            Text(
                text = question.statement,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            question.options.forEach { value ->
                Row {
                    RadioButton(
                        selected = selectedValue.value == value,
                        onClick = { selectedValue.value = value },
                        enabled = enableRadio.value
                    )
                    Text(
                        text = value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically)
                            .padding(vertical = 12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            quizViewModel.getResultQuestion()?.let {
                ResponseQuestionComponent(
                    message = it.message,
                    color = it.backgroundColor,
                    it.textColor
                )
            }

            questionState.result?.let {
                isLoading.value = false
                ButtonComponent(labelText = "Next answer", isLoading) {
                    quizViewModel.getNextQuestion()
                    enableRadio.value = true
                    count.value += 1
                }
            } ?: run {
                val answer = Answer(answer = selectedValue.value)
                ButtonComponent(labelText = "Send answer", isLoading) {
                    quizViewModel.sendQuestion(question.id, answer)
                    enableRadio.value = false
                }
            }

            questionState.result?.let {
                if (it.result) {
                    quizViewModel.finishGame(count) {
                        navHostController.navigate(Screens.ResultScreen.route)
                    }
                }
            }
        }
    }
}