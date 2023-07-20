package com.example.quizdynamox.data.repository

import com.example.quizdynamox.Question
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuestion(): Flow<Question>
}