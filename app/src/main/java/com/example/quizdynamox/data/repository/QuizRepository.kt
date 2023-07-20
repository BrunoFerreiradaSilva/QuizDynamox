package com.example.quizdynamox.data.repository

import com.example.quizdynamox.helpers.DataState
import com.example.quizdynamox.model.entity.Answer
import com.example.quizdynamox.model.entity.QuestionEntity
import com.example.quizdynamox.model.entity.Result
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuestion(): Flow<DataState<QuestionEntity>>
    fun sendQuestion(idQuestion: Int, answer: Answer): Flow<DataState<Result>>
}