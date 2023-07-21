package com.example.quizdynamox.data.database


import androidx.room.*
import com.example.quizdynamox.model.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface PlayerDAO {

    @Query("SELECT * from `playerentity`")
    fun getAllPlayers(): Flow<PlayerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: PlayerEntity)

}
