package com.example.quizdynamox.ui.screens.quiz

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizdynamox.data.repository.player.PlayerRepository
import com.example.quizdynamox.data.repository.quiz.QuizRepository
import com.example.quizdynamox.helpers.DataState
import com.example.quizdynamox.model.entity.Answer
import com.example.quizdynamox.model.entity.QuestionEntity
import com.example.quizdynamox.model.entity.ResponseResult
import com.example.quizdynamox.model.entity.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class QuizUiData(
    val quiz: QuestionEntity? = null,
    val result: Result? = null
)

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuizRepository,
    private val playerRepository: PlayerRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<QuizUiData> =
        MutableStateFlow(QuizUiData())

    val uiState = _uiState.asStateFlow()
    private var count = 1
    private var result = 0

    init {
        viewModelScope.launch {
            repository.getQuestion().collect(::handleGetQuestion)
        }
    }

    fun getNextQuestion(stopLoading: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.getQuestion().collect(::handleGetQuestion)
            count++
            stopLoading(true)
        }
    }

    private fun handleGetQuestion(state: DataState<QuestionEntity>) {
        when (state) {
            is DataState.Data -> {
                _uiState.value = QuizUiData(quiz = state.data)
            }

            is DataState.Error -> {

            }

            is DataState.Loading -> {

            }
        }
    }

    fun finishGame(): Boolean {
        return count == 10
    }

    fun correctAnswer(): Int {
        ++result
        return result / 2
    }

    fun sendQuestion(idQuestion: Int, answer: Answer, result: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            if (validateChoice(answer.answer.isNotEmpty())) {
                repository.sendQuestion(idQuestion = idQuestion, answer = answer)
                    .collect(::getResultQuestion)
            }
            result(answer.answer.isEmpty(), "Selecione uma pergunta !")
        }
    }

    private fun getResultQuestion(state: DataState<Result>) {
        when (state) {
            is DataState.Data -> {
                val quiz = _uiState.value.quiz
                _uiState.value = QuizUiData(quiz = quiz, result = state.data)
            }

            is DataState.Error -> {}
            is DataState.Loading -> {}
        }
    }

    fun getResult(): ResponseResult? {
        var responseResult: ResponseResult? = null
        _uiState.value.result?.let {
            responseResult = if (it.result) {
                ResponseResult(
                    backgroundColor = Color.Green,
                    message = "Resposta correta",
                    textColor = Color.Black
                )
            } else {
                ResponseResult(
                    backgroundColor = Color.Red,
                    message = "Resposta incorreta",
                    textColor = Color.White
                )
            }
        }
        return responseResult
    }

    private fun validateChoice(isSelected: Boolean): Boolean {
        return isSelected
    }
}