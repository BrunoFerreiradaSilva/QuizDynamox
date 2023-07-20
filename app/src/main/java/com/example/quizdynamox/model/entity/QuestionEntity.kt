package com.example.quizdynamox.model.entity

data class QuestionEntity(
    val id: Int,
    val statement: String,
    val options: List<String>,
    val isFinishGame:Boolean = false
)
