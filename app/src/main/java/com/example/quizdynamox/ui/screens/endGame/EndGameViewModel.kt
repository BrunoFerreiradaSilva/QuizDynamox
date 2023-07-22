package com.example.quizdynamox.ui.screens.endGame

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizdynamox.NAME_PLAYER_INTENT
import com.example.quizdynamox.SCORE_PLAYER_INTENT
import com.example.quizdynamox.data.repository.player.PlayerRepository
import com.example.quizdynamox.helpers.DataState
import com.example.quizdynamox.model.entity.PlayerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlayerUiData(
    val playerName: String = "",
    val playerScore: Int = 0,
    val allPlayerEntities: List<PlayerEntity> = emptyList()
)

@HiltViewModel
class EndGameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: PlayerRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<PlayerUiData> =
        MutableStateFlow(PlayerUiData())

    val uiState = _uiState.asStateFlow()

    private val nameUser: String = checkNotNull(savedStateHandle[NAME_PLAYER_INTENT])
    private val score: Int = checkNotNull(savedStateHandle[SCORE_PLAYER_INTENT])

    init {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(playerScore = score, playerName = nameUser)
            repository.getAllPlayers().collect(::handleGetAllPlayers)
        }
    }

    fun insertPlayerRoom() {
        viewModelScope.launch {
            delay(300)
            repository.insertPlayer(PlayerEntity(name = nameUser, score = score))
        }
    }

    private fun handleGetAllPlayers(state: DataState<List<PlayerEntity>?>) {
        when (state) {
            is DataState.Data -> {
                state.data?.let {
                    _uiState.value = _uiState.value.copy(
                        allPlayerEntities = it
                    )
                }

            }

            is DataState.Error -> {}
            is DataState.Loading -> {}
        }
    }
}