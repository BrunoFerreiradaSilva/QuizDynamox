package com.example.quizdynamox.data.repository

import com.example.quizdynamox.model.entity.QuestionEntity
import com.example.quizdynamox.data.service.QuizService
import com.example.quizdynamox.helpers.DataState
import com.example.quizdynamox.helpers.LoadingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuizRepositoryImp @Inject constructor(private val quizService: QuizService) : QuizRepository {
    override fun getQuestion(): Flow<DataState<QuestionEntity>> = flow {
        emit(DataState.Loading(LoadingState.Loading))
        val questionDto = quizService.getQuestions()
        val questionEntity = QuestionEntity(
            id = questionDto.id,
            statement = questionDto.statement,
            options = questionDto.options
        )
        emit(DataState.Data(data = questionEntity))
    }
}