package com.example.quizdynamox.data.repository

import com.example.quizdynamox.Question
import com.example.quizdynamox.data.service.Service
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizRepositoryImp @Inject constructor(private val service:Service): QuizRepository {
    override fun getQuestion(): Flow<Question> {
        TODO("Not yet implemented")
    }
}