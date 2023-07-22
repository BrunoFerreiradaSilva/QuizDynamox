package com.example.quizdynamox.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizdynamox.data.repository.player.PlayerRepository
import com.example.quizdynamox.model.entity.PlayerEntity
import com.example.quizdynamox.ui.screens.quiz.QuizzUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class HomeUiState(
    val nameUser: String? = null,
    val showErrorName: Boolean? = null,
    val fieldValid: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PlayerRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> =
        MutableStateFlow(HomeUiState())

    val uiState = _uiState.asStateFlow()


    fun validateName(name: String, isInvalid: (Boolean) -> Unit) {
        val invalidName = name.isEmpty() && name.isBlank()

        _uiState.value =
            _uiState.value.copy(nameUser = name, showErrorName = invalidName, fieldValid = invalidName)

        isInvalid(invalidName)
    }
}