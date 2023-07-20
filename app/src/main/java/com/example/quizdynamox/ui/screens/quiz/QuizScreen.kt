package com.example.quizdynamox.ui.screens.quiz

import android.app.Activity
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quizdynamox.Question
import com.example.quizdynamox.getQuestions
import com.example.quizdynamox.navigation.Screens
import com.example.quizdynamox.ui.components.ButtonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun QuizScreen(navHostController: NavHostController, nameUser: String?) {
    val quizViewModel = hiltViewModel<QuizViewModel>()
    val questionState by quizViewModel.uiState.collectAsState()

    val result = remember { mutableStateOf(0) }
    val isLoading = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (questionState) {
            QuizUiState.Error -> {}
            QuizUiState.Loading -> {}
            is QuizUiState.Success -> {
                val question = (questionState as QuizUiState.Success).question

                question?.let {
                    if (it.isFinalQuestion) {
                        FinishScreen(nameUser, result, navHostController, isLoading)
                    } else {
                        GameScreen(it, result, isLoading)
                    }
                }
            }
        }
    }
}

@Composable
private fun GameScreen(
    question: Question?,
    result: MutableState<Int>,
    isLoading: MutableState<Boolean>
) {
    val selectedValue = remember { mutableStateOf("") }

    val coroutineScope = CoroutineScope(Dispatchers.IO + Job())
    val count = remember { mutableStateOf(0) }
    val isResultOk = remember { mutableStateOf(false) }

    question?.let {
        Text(text = it.statement, modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        it.options.forEach { value ->
            Row() {
                RadioButton(
                    selected = selectedValue.value == value,
                    onClick = { selectedValue.value = value },
                )
                Text(
                    text = value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                )
            }
            isResultOk.value = it.response == selectedValue.value
        }

        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        ButtonComponent(labelText = "Next Question", isLoading) {
            count.value += 1

            coroutineScope.launch {
                delay(1500)

                isLoading.value = false
            }
        }
    }


}

@Composable
private fun FinishScreen(
    nameUser: String?,
    result: MutableState<Int>,
    navHostController: NavHostController,
    isLoading: MutableState<Boolean>
) {
    nameUser?.let {
        Text(text = nameUser)
    }
    Text(text = "Sua Pontuação foi de ${result.value}")
    Spacer(modifier = Modifier.padding(vertical = 10.dp))
    ButtonComponent(labelText = "Jogar Denovo", isLoading) {
        navHostController.navigate(Screens.InitialScreen.route)
    }
}