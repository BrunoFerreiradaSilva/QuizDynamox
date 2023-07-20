package com.example.quizdynamox.model.dto

data class QuestionDTO(
    val id: Int,
    val statement: String,
    val options: List<String>,
)
