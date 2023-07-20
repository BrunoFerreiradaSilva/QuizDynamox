package com.example.quizdynamox.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val name:String = "",
    val questionsAnswer: Int = 0,
    val score:Int = 0,
    val completeQuiz: Boolean? = null
)
