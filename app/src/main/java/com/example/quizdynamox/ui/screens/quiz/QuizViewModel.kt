package com.example.quizdynamox.ui.screens.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizdynamox.data.repository.quiz.QuizRepository
import com.example.quizdynamox.helpers.DataState
import com.example.quizdynamox.model.entity.Question
import com.example.quizdynamox.model.entity.ResultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class QuizUiState(
    val isLoading: Boolean = true,
    val showError: Boolean = false,
    val showData: Boolean = false,
    val statement: String = "",
    val options: List<OptionUi> = emptyList(),
    val questionId: Int?,
    val showSendButton: Boolean = true,
    val showNextButton: Boolean = false,
    val showResult: Boolean? = null,
    val finishTheGame: Boolean = false,
    val showMessageError: Boolean = false,
    val tryAgain: Boolean = false,
    val progressCount: Int = 0,
    val currentProgress: Float = progressCount.toFloat() / MAX_QUESTIONS,
    val score: Int = 0
)

data class OptionUi(
    val isSelected: Boolean = false,
    val text: String = "",
    val index: Int = -1,
    val isEnabled: Boolean = true
)

const val MAX_QUESTIONS = 10

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuizRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<QuizUiState> =
        MutableStateFlow(QuizUiState(questionId = null))

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getQuestion().collect(::handleGetQuestion)
        }
    }

    fun getNextQuestion() {
        viewModelScope.launch {
            isFinishGame()
            repository.getQuestion().collect(::handleGetQuestion)
        }
    }

    fun retryGetQuestion() {
        viewModelScope.launch {
            repository.getQuestion().collect(::handleGetQuestion)
        }
    }

    private fun updateProgress() {
        val updateProgressCount = _uiState.value.progressCount + 1
        _uiState.value = _uiState.value.copy(
            progressCount = updateProgressCount,
            currentProgress = updateProgressCount.toFloat() / MAX_QUESTIONS
        )
    }

    private fun isFinishGame() {
        val finishTheGame = _uiState.value.progressCount >= MAX_QUESTIONS
        _uiState.value = _uiState.value.copy(finishTheGame = finishTheGame)
    }

    private fun handleGetQuestion(state: DataState<Question>) {
        when (state) {
            is DataState.Data -> {
                viewModelScope.launch {

                    val optionsUi = state.data.options.mapIndexed { index, optionText ->
                        OptionUi(
                            isSelected = false,
                            index = index,
                            text = optionText
                        )
                    }

                    _uiState.value = QuizUiState(
                        questionId = state.data.id,
                        isLoading = false,
                        showData = true,
                        statement = state.data.statement,
                        options = optionsUi,
                        showMessageError = _uiState.value.showMessageError,
                        progressCount = _uiState.value.progressCount,
                        currentProgress = _uiState.value.currentProgress,
                        score = _uiState.value.score
                    )
                }
                updateProgress()
            }

            is DataState.Error -> {
                _uiState.value =
                    QuizUiState(
                        questionId = null,
                        isLoading = false,
                        showError = true,
                        showMessageError = _uiState.value.showMessageError,
                        tryAgain = true,
                        progressCount = _uiState.value.progressCount,
                        currentProgress = _uiState.value.currentProgress,
                        score = _uiState.value.score
                    )
            }

            is DataState.Loading -> {
                _uiState.value = QuizUiState(
                    questionId = null,
                    isLoading = true,
                    finishTheGame = _uiState.value.finishTheGame,
                    showMessageError = _uiState.value.showMessageError,
                    progressCount = _uiState.value.progressCount,
                    currentProgress = _uiState.value.currentProgress,
                    score = _uiState.value.score
                )
            }
        }
    }

    fun selectOptions(index: Int) {
        val optionsUpdate = _uiState.value.options.map {
            if (it.index == index) {
                OptionUi(
                    index = it.index,
                    isSelected = !it.isSelected,
                    text = it.text,
                )
            } else {
                OptionUi(
                    index = it.index,
                    isSelected = false,
                    text = it.text,
                )
            }
        }

        _uiState.value = uiState.value.copy(options = optionsUpdate)
    }

    fun sendSelectedOptions(questionId: Int?) {
        val selectedOption: OptionUi? = _uiState.value.options.firstOrNull {
            it.isSelected
        }

        selectedOption?.let {
            viewModelScope.launch {
                questionId?.let { questionId ->
                    repository.sendSelectedOption(
                        questionId = questionId,
                        selectedOptionText = selectedOption.text
                    )
                        .collect(::getResultQuestion)
                }
            }
            _uiState.value = _uiState.value.copy(showMessageError = false)
        } ?: run {
            _uiState.value = _uiState.value.copy(showMessageError = true)
        }
    }

    private fun getResultQuestion(state: DataState<ResultResponse>) {
        when (state) {
            is DataState.Data -> {
                val disabledOptions = _uiState.value.options.map {
                    OptionUi(
                        isEnabled = false,
                        index = it.index,
                        text = it.text,
                        isSelected = it.isSelected
                    )
                }

                val score =
                    if (state.data.result) _uiState.value.score + 1 else _uiState.value.score

                _uiState.value = _uiState.value.copy(
                    options = disabledOptions,
                    showNextButton = true,
                    showSendButton = false,
                    showResult = state.data.result,
                    score = score
                )
            }

            is DataState.Error -> {}
            is DataState.Loading -> {}
        }
    }
}