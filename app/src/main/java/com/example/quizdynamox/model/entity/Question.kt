package com.example.quizdynamox.model.entity

import androidx.compose.ui.graphics.Color

data class Question(
    val id: Int,
    val statement: String,
    val options: List<String>,
    val isFinishGame:Boolean = false,
    val maxQuestions:Int = 10
)

data class AnswerRequest(
    val answer:String
)

data class Result(
    val result: Boolean
)

