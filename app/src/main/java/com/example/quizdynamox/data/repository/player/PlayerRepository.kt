package com.example.quizdynamox.data.repository.player

import com.example.quizdynamox.helpers.DataState
import com.example.quizdynamox.model.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    suspend fun insertPlayer(player:PlayerEntity)
    fun getAllPlayers(): Flow<DataState<List<PlayerEntity>?>>
}