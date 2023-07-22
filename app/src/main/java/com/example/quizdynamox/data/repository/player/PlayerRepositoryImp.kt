package com.example.quizdynamox.data.repository.player

import com.example.quizdynamox.data.database.PlayerDAO
import com.example.quizdynamox.helpers.DataState
import com.example.quizdynamox.model.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayerRepositoryImp @Inject constructor(private val dao: PlayerDAO) : PlayerRepository {
    override suspend fun insertPlayer(playerEntity: PlayerEntity) {
        dao.insertPlayer(playerEntity = playerEntity)
    }

    override fun getAllPlayers(): Flow<DataState<List<PlayerEntity>>> = flow {
        dao.getAllPlayers().collect { listPlayer ->
            listPlayer?.let { player ->
                emit(DataState.Data(data = player))
            } ?: run {
                emit(DataState.Data(data = emptyList()))
            }
        }
    }

}