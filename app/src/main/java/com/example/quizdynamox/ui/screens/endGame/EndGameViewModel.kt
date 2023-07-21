package com.example.quizdynamox.ui.screens.endGame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizdynamox.data.repository.player.PlayerRepository
import com.example.quizdynamox.helpers.DataState
import com.example.quizdynamox.model.entity.PlayerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlayerUiData(
    val players: List<PlayerEntity> = listOf()
)

@HiltViewModel
class EndGameViewModel @Inject constructor(private val repository: PlayerRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<PlayerUiData> =
        MutableStateFlow(PlayerUiData())

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllPlayers().collect(::handleGetAllPlayers)
        }
    }

    private fun handleGetAllPlayers(state: DataState<PlayerEntity?>) {
        val listPlayers = mutableListOf<PlayerEntity>()
        when (state) {
            is DataState.Data -> {
                state.data?.let { player ->
                    listPlayers.add(player)
                    _uiState.value = PlayerUiData(listPlayers)
                }
            }

            is DataState.Error -> {}
            is DataState.Loading -> {}
        }
    }

}