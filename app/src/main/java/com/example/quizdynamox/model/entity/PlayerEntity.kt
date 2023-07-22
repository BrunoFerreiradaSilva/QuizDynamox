package com.example.quizdynamox.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val name:String = "",
    val score:Int = 0,
)
