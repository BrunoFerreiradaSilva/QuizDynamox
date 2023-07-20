package com.example.quizdynamox.data.repository

import com.example.quizdynamox.model.entity.QuestionEntity
import com.example.quizdynamox.helpers.DataState
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuestion(): Flow<DataState<QuestionEntity>>
}