package com.example.quizdynamox.model.entity

data class Question(
    val id: Int,
    val statement: String,
    val options: List<String>
)

data class AnswerRequest(
    val answer:String
)

data class ResultResponse(
    val result: Boolean
)

