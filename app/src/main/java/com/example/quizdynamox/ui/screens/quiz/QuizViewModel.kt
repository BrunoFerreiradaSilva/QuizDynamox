package com.example.quizdynamox.ui.screens.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizdynamox.Question
import com.example.quizdynamox.data.repository.QuizRepository
import com.example.quizdynamox.helpers.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface QuizUiState {
    object Loading : QuizUiState
    data class Success(var question: Question?) : QuizUiState
    object Error : QuizUiState
}

@HiltViewModel
class QuizViewModel @Inject constructor(private val repository: QuizRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<QuizUiState> =
        MutableStateFlow(QuizUiState.Loading)

    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            repository.getQuestion().collect(::handleGetQuestion)
        }
    }

    private fun handleGetQuestion(state: DataState<Question>) {
        when(state){
            is DataState.Data -> {
                _uiState.value = QuizUiState.Success(state.data)
            }
            is DataState.Error -> {
                QuizUiState.Loading
            }
            is DataState.Loading -> {
                QuizUiState.Error
            }
        }
    }
}