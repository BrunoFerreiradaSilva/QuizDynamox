package com.example.quizdynamox.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizdynamox.data.repository.player.PlayerRepository
import com.example.quizdynamox.model.entity.PlayerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: PlayerRepository) : ViewModel() {
    private val uuid = UUID.randomUUID().toString()

    fun insertPlayer(namePlayer: String) {
        viewModelScope.launch {
            val player = PlayerEntity(
                name = namePlayer,
                completeQuiz = false,
                uuid = uuid
            )
            repository.insertPlayer(player)
        }
    }

    fun validateName(name: String): Boolean {
        return name.isNotEmpty()
    }
}