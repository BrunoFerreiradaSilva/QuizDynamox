package com.example.quizdynamox.ui.screens.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quizdynamox.model.entity.Answer
import com.example.quizdynamox.navigation.Screens
import com.example.quizdynamox.ui.components.ButtonComponent
import com.example.quizdynamox.ui.components.ResponseQuestionComponent

@Composable
fun QuizScreen(navHostController: NavHostController, userName: String?) {
    val quizViewModel = hiltViewModel<QuizViewModel>()
    val questionState by quizViewModel.uiState.collectAsState()

    val selectedValue = remember { mutableStateOf("") }
    val count = remember { mutableStateOf(1) }
    val isLoading = remember { mutableStateOf(false) }
    val enableRadio = remember { mutableStateOf(true) }
    val score = remember { mutableStateOf(0) }
    val endGame = remember {
        mutableStateOf(false)
    }

    if (endGame.value) {
        if (userName != null) {
            EndGameScreen(
                userName = userName,
                score = score.value,
                navHostController = navHostController,
                isLoading = isLoading
            )
        }
    } else {
        questionState.quiz?.let { question ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Text(
                    text = "Pergunta",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Column(Modifier.padding(vertical = 12.dp)) {
                    Text(
                        text = question.statement,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))


                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Resposta",
                        modifier = Modifier.padding(vertical = 10.dp),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    val width = LocalConfiguration.current.screenWidthDp.dp


                    LazyColumn(modifier = Modifier.size(width)) {
                        items(question.options) { item ->
                            Row {
                                RadioButton(
                                    selected = selectedValue.value == item,
                                    onClick = { selectedValue.value = item },
                                    enabled = enableRadio.value
                                )
                                Text(
                                    text = item,
                                    modifier = Modifier
                                        .padding(vertical = 16.dp)
                                        .align(Alignment.Top)
                                )
                            }
                        }
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    quizViewModel.getResult()?.let {
                        ResponseQuestionComponent(
                            message = it.message,
                            color = it.backgroundColor,
                            it.textColor
                        )
                    }

                    questionState.result?.let {
                        isLoading.value = false
                        if (it.result) {
                            score.value = quizViewModel.correctAnswer()
                        }
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
                }
            }
        }

    }

    quizViewModel.finishGame(count) {
        if (it) {
            endGame.value = true
        }
    }
}

@Composable
fun EndGameScreen(
    userName: String?,
    score: Int,
    navHostController: NavHostController,
    isLoading: MutableState<Boolean>
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (userName != null) {
            Text(text = userName)
        }
        Text(text = "Sua Pontuação foi de $score")

        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        ButtonComponent(labelText = "Jogar Denovo", isLoading) {
            navHostController.navigate(Screens.InitialScreen.route)
        }
    }
}

