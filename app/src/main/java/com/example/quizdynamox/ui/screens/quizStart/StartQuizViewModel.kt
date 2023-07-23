package com.example.quizdynamox.ui.screens.quizStart

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class HomeUiState(
    val nameUser: String? = null,
    val showErrorName: Boolean? = null,
    val fieldValid: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> =
        MutableStateFlow(HomeUiState())

    val uiState = _uiState.asStateFlow()


    fun validateName(name: String, isInvalid: (Boolean) -> Unit) {
        val invalidName = name.isEmpty() && name.isBlank()

        _uiState.value =
            _uiState.value.copy(
                nameUser = name,
                showErrorName = invalidName,
                fieldValid = invalidName
            )

        isInvalid(invalidName)
    }
}