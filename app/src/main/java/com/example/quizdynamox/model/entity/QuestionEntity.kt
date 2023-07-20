package com.example.quizdynamox.model.entity

import android.text.style.BackgroundColorSpan
import androidx.compose.ui.graphics.Color

data class QuestionEntity(
    val id: Int,
    val statement: String,
    val options: List<String>,
    val isFinishGame:Boolean = false,
    val maxQuestions:Int = 9
)

data class Answer(
    val answer:String
)

data class Result(
    val result: Boolean
)

data class ResponseResult(
    val message:String,
    val backgroundColor:Color,
    val textColor:Color
)

