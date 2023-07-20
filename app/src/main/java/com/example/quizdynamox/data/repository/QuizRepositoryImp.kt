package com.example.quizdynamox.data.repository

import com.example.quizdynamox.Question
import com.example.quizdynamox.data.service.QuizService
import com.example.quizdynamox.helpers.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuizRepositoryImp @Inject constructor(private val quizService: QuizService) : QuizRepository {
    override fun getQuestion(): Flow<DataState<Question>> = flow {
        val question = quizService.getQuestions()
        emit(DataState.Data(data = question))
    }
}