package com.example.quizdynamox.ui.screens.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Colors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    val width = LocalConfiguration.current.screenWidthDp.dp+25.dp

    val selectedValue = remember { mutableStateOf("") }
    val messageError = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }
    val enableRadio = remember { mutableStateOf(true) }


    if (quizViewModel.finishGame()) {
        EndGameScreen(
            userName = userName,
            score = quizViewModel.correctAnswer(),
            navHostController = navHostController,
            isLoading = isLoading
        )
    } else {
        questionState.quiz?.let { question ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
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
                        text = question.statement,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
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

                    Text(text = messageError.value)
                    quizViewModel.getResult()?.let {
                        ResponseQuestionComponent(
                            message = it.message,
                            color = it.backgroundColor,
                            it.textColor
                        )
                    }

                    questionState.result?.let {
                        isLoading.value = false
                        messageError.value = ""
                        ButtonComponent(labelText = "Next answer", isLoading) {
                            quizViewModel.getNextQuestion() { loading ->
                                isLoading.value = !loading
                                enableRadio.value = loading
                                selectedValue.value = ""
                            }
                        }
                        if (it.result) {
                            quizViewModel.correctAnswer()
                        }
                    } ?: run {
                        val answer = Answer(answer = selectedValue.value)
                        ButtonComponent(labelText = "Send answer", isLoading) {
                            quizViewModel.sendQuestion(question.id, answer) { enabled, message ->
                                enableRadio.value = enabled
                                isLoading.value = !enabled
                                messageError.value = message
                            }
                        }
                    }

                }
            }
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

