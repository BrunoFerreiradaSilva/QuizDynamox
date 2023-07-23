package com.example.quizdynamox.ui.screens.quizStart

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class StartQuizUiState(
    val nameUser: String? = null,
    val isErrorName:Boolean = false,
    val fieldValid: Boolean = false
)

@HiltViewModel
class StartQuizViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<StartQuizUiState> =
        MutableStateFlow(StartQuizUiState())

    val uiState = _uiState.asStateFlow()


    fun validateName(name: String) {
        val invalidName = name.isEmpty() && name.isBlank()
        _uiState.value =
            _uiState.value.copy(
                nameUser = name,
                isErrorName = invalidName,
                fieldValid = invalidName
            )
    }
}