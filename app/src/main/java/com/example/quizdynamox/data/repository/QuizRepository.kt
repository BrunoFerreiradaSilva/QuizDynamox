package com.example.quizdynamox.data.repository

import com.example.quizdynamox.Question
import com.example.quizdynamox.helpers.DataState
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuestion(): Flow<DataState<Question>>
}